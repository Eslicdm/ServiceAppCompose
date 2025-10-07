package com.eslirodrigues.serviceappcompose

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform