package com.albatros.olimp.domain.use_case

import com.albatros.olimp.data.repo.VkServiceRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetVkServicesListUseCase @Inject constructor(private val vkServiceRepository: VkServiceRepository) {
    suspend operator fun invoke() = withContext(Dispatchers.IO) {
        vkServiceRepository.getVkServices()
    }
}