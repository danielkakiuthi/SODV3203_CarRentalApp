package com.example.sodv3203_carrentalapp.ui

import androidx.lifecycle.ViewModel
import com.example.sodv3203_carrentalapp.data.AppUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class AppViewModel : ViewModel() {

    // Define State
    private val _uiState = MutableStateFlow(AppUiState())
    val uiState : StateFlow<AppUiState> = _uiState.asStateFlow()

    // Define Custom Methods



}