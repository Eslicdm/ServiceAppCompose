//import io.ktor.client.*
//import io.ktor.client.call.*
//import io.ktor.client.engine.cio.*
//import io.ktor.client.plugins.contentnegotiation.*
//import io.ktor.client.request.forms.*
//import io.ktor.http.Parameters
//import io.ktor.serialization.kotlinx.json.*
//
//// This function would create the client you use everywhere else in your app
//fun createAuthenticatedClient(initialTokens: BearerTokens): HttpClient {
//    return HttpClient(CIO) {
//        install(ContentNegotiation) {
//            json()
//        }
//
//        install(com.google.android.gms.auth.api.Auth) {
//            bearer {
//                // 1. LOAD TOKENS: This block provides the tokens Ktor will use for requests.
//                loadTokens {
//                    // Load tokens from your secure storage (EncryptedSharedPreferences, etc.)
//                    // For this example, we're just using the ones from the initial login.
//                    initialTokens
//                }
//
//                // 2. REFRESH TOKENS: This block is called when a request fails with 401 Unauthorized.
//                refreshTokens {
//                    // Create a temporary, un-authenticated client to refresh the token.
//                    val refreshClient = HttpClient(CIO) {
//                        install(ContentNegotiation) { json() }
//                    }
//
//                    // Perform the token refresh request to Keycloak.
//                    val refreshTokenInfo: TokenResponse = refreshClient.submitForm(
//                        url = "http://keycloak:8080/realms/service-app-realm/protocol/openid-connect/token",
//                        formParameters = Parameters.build {
//                            append("grant_type", "refresh_token")
//                            append("client_id", "service-app-compose")
//                            append("refresh_token", oldTokens?.refreshToken ?: "")
//                        }
//                    ).body()
//
//                    // TODO: Save the new tokens to your secure storage.
//
//                    // Return the new tokens to the Auth plugin.
//                    BearerTokens(
//                        accessToken = refreshTokenInfo.accessToken,
//                        refreshToken = refreshTokenInfo.refreshToken
//                    )
//                }
//            }
//        }
//    }
//}
