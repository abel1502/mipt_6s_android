package com.abel.mipt_6s_test_app.net

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.request
import io.ktor.http.HttpMethod
import io.ktor.http.URLBuilder
import javax.inject.Inject

class RestaurantRepository @Inject constructor(private val httpClient: HttpClient) {
    companion object {
        private const val API_URL = "http://195.2.84.138:8081/catalog"
    }

    suspend fun fetchCatalog(): CatalogResponse {
        return httpClient.request(API_URL) {
            method = HttpMethod.Get
        }.body()
    }
}