package edu.virginia.cs.helloworldhw.ui.theme.add_item

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import edu.virginia.cs.helloworldhw.util.UiEvent



@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AddItemScreen(
    onPopBackStack: () -> Unit,
    viewModel: AddItemViewModel = hiltViewModel()
){
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect{ event ->
            when(event) {
                is UiEvent.PopBackStack -> onPopBackStack()
                else -> {}
            }

        }
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        floatingActionButton = {
            FloatingActionButton(onClick = { viewModel.onEvent(AddItemEvent.onSave) }
            ) {
                Icon(Icons.Default.Done, contentDescription = "Save button")
            }
        }
    ){ innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            OutlinedTextField(
                value = viewModel.name,
                onValueChange = {
                    viewModel.onEvent(AddItemEvent.onContentEdit(it))
                },
                label = { Text("Bucketlist Item:") },
                placeholder = { Text( text = "Enter New Bucketlist Item Here.") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = viewModel.dueDate,
                onValueChange = {
                    viewModel.onEvent(AddItemEvent.onDueDateEdit(it))
                },
                label = { Text("Due Date:") },
                placeholder = { Text( text = "Enter Due Date Here. No Spaces. (Example: YYYYMMDD)") },
                modifier = Modifier.fillMaxWidth(),
                maxLines = 5
            )

            Spacer(modifier = Modifier.height(8.dp))
            Row( modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)) {

                Button(onClick = { onPopBackStack() }) {
                    Text("Cancel")
                }
                Spacer(modifier = Modifier.width(7.dp))
                //Text(text= "Completed Date: " + viewModel.dateCompleted)

            }

        }
    }

}


