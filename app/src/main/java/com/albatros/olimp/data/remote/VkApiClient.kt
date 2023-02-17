package com.albatros.olimp.data.remote

import com.albatros.olimp.data.dto.VkServicesListDto
import retrofit2.http.GET

interface VkApiClient {
    @GET(value = "/semi-final-data.json")
    suspend fun getVkServicesList(): VkServicesListDto
}