package com.albatros.olimp.data.remote

import com.albatros.olimp.domain.model.VkServicesList
import retrofit2.http.GET

interface VkApiClient {
    @GET(value = "/semi-final-data.json")
    suspend fun getVkServicesList(): VkServicesList
}