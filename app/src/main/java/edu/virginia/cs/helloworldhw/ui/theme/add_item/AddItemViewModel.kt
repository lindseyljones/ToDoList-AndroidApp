package edu.virginia.cs.helloworldhw.ui.theme.add_item

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.virginia.cs.helloworldhw.data.Bucketlist
import edu.virginia.cs.helloworldhw.data.BucketlistRepository
import edu.virginia.cs.helloworldhw.util.UiEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddItemViewModel @Inject constructor(
    private val bucketlistRepository: BucketlistRepository,
    savedStateHandle: SavedStateHandle // contains a bunch of state variables, contains navigation arguments
): ViewModel() {
    var bucketlist by mutableStateOf<Bucketlist?>(null)
        private set
    var name by mutableStateOf("")
        private set
    var dateCompleted by mutableStateOf(value="")
        private set
    var dueDate by mutableStateOf("")
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        val itemId = savedStateHandle.get<Int>("itemId")!!
        if (itemId != -1){ // there is an existing in db
            viewModelScope.launch(){
                bucketlistRepository.getItemById(itemId)?.let { bucketlist ->
                    name = bucketlist.name
                    dueDate = bucketlist.dueDate
                    this@AddItemViewModel.bucketlist = bucketlist
                }

            }
        }
    }

    fun onEvent(event: AddItemEvent){
        when(event) {
            is AddItemEvent.onContentEdit -> {name = event.name}
            is AddItemEvent.onDueDateEdit -> {dueDate = event.dueDate}
            is AddItemEvent.onSave -> {
                viewModelScope.launch{
                    if(name.isBlank()) {
                        return@launch
                    }
                    bucketlistRepository.insertItem(
                        Bucketlist(
                            name = name,
                            dueDate = dueDate,
                            isChecked = bucketlist?.isChecked ?: false,
                            dateCompleted = dateCompleted ?: "",
                            id = bucketlist?.id,
                        )
                    )
                    sendUiEvent(UiEvent.PopBackStack)
                }
            }
        }
    }

    private fun sendUiEvent(event: UiEvent){
        viewModelScope.launch{
            _uiEvent.send(event)
        }
    }
}