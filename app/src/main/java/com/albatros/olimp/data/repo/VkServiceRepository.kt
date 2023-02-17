package com.albatros.olimp.data.repo

import com.albatros.olimp.domain.model.VkServicesList

interface VkServiceRepository {
    suspend fun getVkServices(): VkServicesList
}