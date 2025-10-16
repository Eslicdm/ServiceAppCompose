package com.eslirodrigues.serviceappcompose.navigation

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed class ServiceAppNavRoutes {
    @Serializable @SerialName("Landing")
    data object LandingScreen : ServiceAppNavRoutes()
    @Serializable @SerialName("PricingAuth")
    data object PricingAuthScreen : ServiceAppNavRoutes()
    @Serializable @SerialName("MemberAuth")
    data object MemberAuthScreen : ServiceAppNavRoutes()

    @Serializable @SerialName("Member")
    data object MemberScreen : ServiceAppNavRoutes()

    @Serializable @SerialName("Pricing")
    data object PricingScreen : ServiceAppNavRoutes()
}