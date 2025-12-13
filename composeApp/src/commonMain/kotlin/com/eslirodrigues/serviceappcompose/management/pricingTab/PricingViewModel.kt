package com.eslirodrigues.serviceappcompose.management.pricingTab

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eslirodrigues.serviceappcompose.management.pricingTab.model.PriceModel
import com.eslirodrigues.serviceappcompose.management.pricingTab.model.PriceType
import com.eslirodrigues.serviceappcompose.management.pricingTab.model.PriceUpdateDto
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed interface PricingState {
    data object Loading : PricingState
    data class Loaded(val prices: List<PriceModel>) : PricingState
    data class Error(val message: String) : PricingState
}

class PricingViewModel(
    // private val repository: PricingRepository // Inject your repository here
) : ViewModel() {

    private val _state = MutableStateFlow<PricingState>(PricingState.Loading)
    val state: StateFlow<PricingState> = _state.asStateFlow()

    init {
        fetchPrices()
    }

    fun fetchPrices() {
        viewModelScope.launch {
            _state.value = PricingState.Loading
            try {
                delay(1000)
                val mockPrices = listOf(
                    PriceModel(
                        "1", PriceType.FREE, 0.0, "Standard free tier",
                        "2023-01-01", "2023-01-01"
                    )
                )

                _state.value = PricingState.Loaded(mockPrices)
            } catch (e: Exception) {
                _state.value = PricingState.Error("Failed to load prices.")
            }
        }
    }

    fun updatePrice(priceType: PriceType, updateDto: PriceUpdateDto) {
        viewModelScope.launch {
            try {
                // Simulate API Update - Replace with actual Repository call
                // repository.upsertPrice(priceType, updateDto)
                fetchPrices() // Reload after update
            } catch (e: Exception) {
                // Handle error (perhaps add a one-time event/channel for toast messages)
            }
        }
    }
}
