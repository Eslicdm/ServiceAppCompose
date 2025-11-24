package com.eslirodrigues.serviceappcompose.navigation

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed class ServiceAppNavRoutes {
    @Serializable @SerialName("Landing")
    data object LandingScreen : ServiceAppNavRoutes()

    @Serializable @SerialName("Management")
    data object ManagementScreen : ServiceAppNavRoutes()
}