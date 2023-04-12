package com.abel.mipt_6s_test_app.net

import com.abel.mipt_6s_test_app.db.RestaurantDao
import com.abel.mipt_6s_test_app.db.toCommercialEntity
import com.abel.mipt_6s_test_app.db.toRemoteCommercial
import com.abel.mipt_6s_test_app.db.toRemoteRestaurant
import com.abel.mipt_6s_test_app.db.toRestaurantEntity
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.request
import io.ktor.http.HttpMethod
import io.ktor.http.URLBuilder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RestaurantRepository @Inject constructor(
    private val httpClient: HttpClient,
    private val restaurantDao: RestaurantDao,
) {
    companion object {
        private const val API_URL = "http://195.2.84.138:8081/catalog"
    }

    suspend fun fetchCatalog(): Flow<CatalogResponse> {
        return flow {
            if (restaurantDao.isNonEmpty()) {
                val response = CatalogResponse(
                    nearest = restaurantDao.getCategoryRestaurants("nearest")
                        .map { it.toRemoteRestaurant() },
                    popular = restaurantDao.getCategoryRestaurants("popular")
                        .map { it.toRemoteRestaurant() },
                    commercial = restaurantDao.getCommercial()
                        .toRemoteCommercial(),
                )

                emit(response)
            }

            // Now the long web request...
            val response: CatalogResponse = httpClient.request(API_URL) {
                method = HttpMethod.Get
            }.body()

            // TODO: Move below?
            emit(response)

            restaurantDao.insert(
                *response.nearest.map {
                    it.toRestaurantEntity("nearest")
                }.toTypedArray()
            )

            restaurantDao.insert(
                *response.popular.map {
                    it.toRestaurantEntity("popular")
                }.toTypedArray()
            )

            restaurantDao.insert(
                response.commercial.toCommercialEntity()
            )
        }
    }
}