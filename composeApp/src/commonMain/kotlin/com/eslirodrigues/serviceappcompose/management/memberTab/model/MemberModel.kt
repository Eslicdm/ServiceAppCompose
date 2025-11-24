package com.eslirodrigues.serviceappcompose.management.memberTab.model

import kotlinx.serialization.Serializable

@Serializable
data class MemberModel(
    val id: Long = 0,
    val name: String,
    val email: String,
    val birthDate: String,
    val photo: String,
    val serviceType: String,
    val managerId: String? = null
)
