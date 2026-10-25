package com.example.ecoconso.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecoconso.data.api.ApiClient
import com.example.ecoconso.data.models.ApplianceResponse
import com.example.ecoconso.data.models.ConsommationTodayResponse
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class ConsoDataState(
    val consoData: ConsommationTodayResponse,
    val appliances: List<ApplianceResponse>
)

sealed class ConsoDetailUiState {
    object Loading : ConsoDetailUiState()
    data class Success(val data: ConsoDataState) : ConsoDetailUiState()
    data class Error(val message: String) : ConsoDetailUiState()
}

class ConsoDetailViewModel : ViewModel() {
    private val _uiState = MutableStateFlow<ConsoDetailUiState>(ConsoDetailUiState.Loading)
    val uiState: StateFlow<ConsoDetailUiState> = _uiState.asStateFlow()

    init {
        fetchConsoDetails()
    }

    fun fetchConsoDetails() {
        viewModelScope.launch {
            _uiState.value = ConsoDetailUiState.Loading
            try {
                // Animation fluide de chargement progressif
                delay(500)
                val conso = ApiClient.ecoApiService.getConsommationToday()
                val appliances = ApiClient.ecoApiService.getAppliances()
                _uiState.value = ConsoDetailUiState.Success(
                    ConsoDataState(
                        consoData = conso,
                        appliances = appliances
                    )
                )
            } catch (e: Exception) {
                _uiState.value = ConsoDetailUiState.Error(e.message ?: "Erreur lors de la récupération des données")
            }
        }
    }
}
