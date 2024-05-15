package edu.virginia.cs.helloworldhw.ui.theme.bucketlist_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.virginia.cs.helloworldhw.data.BucketlistRepository
import edu.virginia.cs.helloworldhw.util.Routes
import edu.virginia.cs.helloworldhw.util.UiEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BucketlistViewModel @Inject constructor(
    private val repository: BucketlistRepository // pass repository instance

): ViewModel() {
    val activeitems = repository.getItems()
    val checkeditems = repository.getCheckedItems()
    val uncheckeditems = repository.getUncheckedItems()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: BucketlistEvent){
        when(event) {
            is BucketlistEvent.OnListItemClick -> {
                sendUiEvent(UiEvent.Navigate(Routes.ADD_EDIT_LIST_ITEM + "?itemId=${event.bucketlist.id}"))
            }
            is BucketlistEvent.OnAddListItem -> {
                sendUiEvent(UiEvent.Navigate(Routes.ADD_EDIT_LIST_ITEM))
            }

            is BucketlistEvent.DeleteListItem -> {
                viewModelScope.launch{
                    repository.deleteItem(
                        event.bucketlist
                    )
                }
            }
            is BucketlistEvent.AfterChange -> {
                viewModelScope.launch {
                    repository.insertItem(
                        event.bucketlist.copy(
                            isChecked = event.isChecked
                        )
                    )
                }
            }
        }
    }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}