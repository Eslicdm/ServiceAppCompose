package com.eslirodrigues.serviceappcompose.management.pricingTab.model

import kotlinx.serialization.Serializable

@Serializable
enum class PriceType {
    FREE, HALF_PRICE, FULL_PRICE;

    override fun toString(): String = when(this) {
        FREE -> "free"
        HALF_PRICE -> "half-price"
        FULL_PRICE -> "full-price"
    }

    fun toTitleCase(): String = when(this) {
        FREE -> "Free"
        HALF_PRICE -> "Half Price"
        FULL_PRICE -> "Full Price"
    }
}

@Serializable
data class PriceModel(
    val id: String,
    val priceType: PriceType,
    val value: Double,
    val description: String,
    val createdAt: String,
    val updatedAt: String
)

@Serializable
data class PriceUpdateDto(
    val value: Double,
    val description: String
)
