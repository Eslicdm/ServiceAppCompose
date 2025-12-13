package com.eslirodrigues.serviceappcompose.di

import com.eslirodrigues.serviceappcompose.management.memberTab.MemberRepository
import com.eslirodrigues.serviceappcompose.management.memberTab.MemberViewModel
import com.eslirodrigues.serviceappcompose.management.pricingTab.PricingViewModel
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    single {
        HttpClient {
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })
            }
        }
    }

    single { MemberRepository(get()) }
    viewModelOf(::MemberViewModel)
    viewModelOf(::PricingViewModel)
}
