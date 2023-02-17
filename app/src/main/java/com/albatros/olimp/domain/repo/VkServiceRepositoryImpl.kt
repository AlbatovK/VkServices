package com.albatros.olimp.domain.repo

import com.albatros.olimp.data.dto.toDomainObject
import com.albatros.olimp.data.remote.VkApiClient
import com.albatros.olimp.data.repo.VkServiceRepository
import com.albatros.olimp.domain.model.VkServicesList
import javax.inject.Inject

class VkServiceRepositoryImpl @Inject constructor(private val api: VkApiClient) :
    VkServiceRepository {
    override suspend fun getVkServices(): VkServicesList = api.getVkServicesList().toDomainObject()
}