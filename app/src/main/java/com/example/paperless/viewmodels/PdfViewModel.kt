package com.example.paperless.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class PdfViewModel: ViewModel() {
    var isSplashScreen by mutableStateOf(false)
    var showRenameDialog  by mutableStateOf(false)
    var loadingDialog by mutableStateOf(false)

    init {
        viewModelScope.launch {
            delay(2000)
            isSplashScreen=false
        }
    }
}