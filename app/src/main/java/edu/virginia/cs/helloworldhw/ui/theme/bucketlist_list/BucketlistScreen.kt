package edu.virginia.cs.helloworldhw.ui.theme.bucketlist_list

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import edu.virginia.cs.helloworldhw.util.UiEvent


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BucketlistScreen(
    onNavigate: (UiEvent.Navigate) -> Unit,
    viewModel: BucketlistViewModel = hiltViewModel()
    ) {

    // val listitems = viewModel.activeitems.collectAsState(initial = emptyList())
    val checkeditems = viewModel.checkeditems.collectAsState(initial = emptyList())
    val uncheckeditems = viewModel.uncheckeditems.collectAsState(initial = emptyList())


    // executes separately from composable function
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event -> // this block of code triggered every time with call send
            when (event) {
                is UiEvent.Navigate -> onNavigate(event)
                else -> Unit
            }
        }

    }
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = { Text("UVA Bucket List") }
            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.primary,
            ) {
                Text("Copyright Lindsey Jones 2024")
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                viewModel.onEvent(BucketlistEvent.OnAddListItem)
            })
            {
                Icon(Icons.Default.Add, contentDescription = "Add bucketlist item.")
            }
        }
    ) { innerPadding ->
        // Apply the padding globally
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            items(uncheckeditems.value) { bucketlist ->
                ListItem(
                    bucketlist = bucketlist,
                    onEvent = viewModel::onEvent,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            viewModel.onEvent(BucketlistEvent.OnListItemClick(bucketlist))
                        }
                        .padding(16.dp)
                )
            }
            items(checkeditems.value) { bucketlist ->
                ListItem(
                    bucketlist = bucketlist,
                    onEvent = viewModel::onEvent,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            viewModel.onEvent(BucketlistEvent.OnListItemClick(bucketlist))
                        }
                        .padding(16.dp)
                )
            }
        }

    }
}

