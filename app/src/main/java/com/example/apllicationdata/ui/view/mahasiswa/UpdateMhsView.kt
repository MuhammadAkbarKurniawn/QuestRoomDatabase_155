package com.example.apllicationdata.ui.view.mahasiswa

import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.apllicationdata.ui.costumwidget.CustomTopAppBar
import com.example.apllicationdata.ui.viewmodel.DetailMhsViewModel
import com.example.apllicationdata.ui.viewmodel.PenyediaViewModel
import com.example.apllicationdata.ui.viewmodel.UpdateMhsViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun UpdateMhsView(
    onBack: () -> Unit,
    onNavigate: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: UpdateMhsViewModel = viewModel(factory = PenyediaViewModel.Factory)// inisialisasi ViewModel

){
    val uiState = viewModel.updateUiState // ambil ui state dari viewmodel
    val snackbarHostState = remember { SnackbarHostState() } // Snackbarstate
    val coroutineScope = rememberCoroutineScope()

    //Observasi perubahan snackbarMessage
    LaunchedEffect(uiState.snackBarMessage) {
        println("LaunchEffect triggered")
        uiState.snackBarMessage?.let { message->
            println("Snackbar message received: $message")
            coroutineScope.launch {
                println("Launching coroutine for snackbar")
                snackbarHostState.showSnackbar(
                    message = message,
                    duration = SnackbarDuration.Long
                )
                viewModel.resetSnackBarMessage()
            }
        }
    }

    Scaffold (
        modifier = modifier,
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }, // tempatkan snackbar discaffold
        topBar = {
            CustomTopAppBar(
                judul = "Edit Mahasiswa",
                showBackButton = true,
                onBack = onBack,
                modifier = Modifier
            )
        }
    ){ padding ->
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ){
            //isi body
            InsertBodyMhs(
                uiState = uiState,
                onValueChange = { updateEvent->
                    viewModel.updateState(updateEvent) //update state diviewmodel
                },
                onClick = {
                    coroutineScope.launch {
                        if (viewModel.validateFields()) {
                            viewModel.updateData()
                            delay(600)
                            withContext(Dispatchers.Main) {
                                onNavigate() // Navigasi di main thread
                            }
                        }
                    }
                }
            )
        }
    }
}