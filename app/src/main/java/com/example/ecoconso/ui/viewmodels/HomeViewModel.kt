package com.example.ecoconso.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecoconso.data.api.ApiClient
import com.example.ecoconso.data.models.ConsommationTodayResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class HomeUiState {
    object Loading : HomeUiState()
    data class Success(val data: ConsommationTodayResponse) : HomeUiState()
    data class Error(val message: String) : HomeUiState()
}

class HomeViewModel : ViewModel() {
    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        fetchHomeData()
    }

    fun fetchHomeData() {
        viewModelScope.launch {
            _uiState.value = HomeUiState.Loading
            try {
                val response = ApiClient.ecoApiService.getConsommationToday()
                _uiState.value = HomeUiState.Success(response)
            } catch (e: Exception) {
                _uiState.value = HomeUiState.Error(e.message ?: "Erreur de connexion au serveur")
            }
        }
    }
}
