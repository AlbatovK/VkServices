package com.albatros.olimp

import com.albatros.olimp.data.dto.VkServiceDto
import com.albatros.olimp.data.dto.VkServicesListDto
import com.albatros.olimp.data.dto.toDomainObject
import com.albatros.olimp.data.remote.VkApiClient
import com.albatros.olimp.data.repo.VkServiceRepository
import com.albatros.olimp.domain.model.VkService
import com.albatros.olimp.domain.model.VkServicesList
import com.albatros.olimp.domain.repo.VkServiceRepositoryImpl
import com.albatros.olimp.domain.use_case.GetVkServicesListUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.*
import kotlin.streams.toList


class UnitTests {

    private val testService = VkService("TEST1", "TEST2", "TEST3", "TEST4")
    private val testServiceDto = VkServiceDto("TEST1", "TEST2", "TEST3", "TEST4")

    private val testCoroutineScope = CoroutineScope(Dispatchers.Unconfined)

    private val testRepo1 = object : VkServiceRepository {
        override suspend fun getVkServices(): VkServicesList {
            val services = emptyList<VkService>()
            return VkServicesList(services)
        }
    }

    private val testRepo2 = object : VkServiceRepository {
        override suspend fun getVkServices(): VkServicesList {
            val services = listOf(testService)
            return VkServicesList(services)
        }
    }

    private val testApi1 = object : VkApiClient {
        override suspend fun getVkServicesList(): VkServicesListDto {
            val services = listOf(testServiceDto)
            return VkServicesListDto(services)
        }
    }

    private val testApi2 = object : VkApiClient {
        override suspend fun getVkServicesList(): VkServicesListDto {
            val services = emptyList<VkServiceDto>()
            return VkServicesListDto(services)
        }
    }

    @Test
    fun test_DtoToDomainObject_isCorrect() {
        val testDtoList = Random().ints().limit(20).mapToObj {
            VkServiceDto(it.toString(), "", "", "")
        }.toList()

        val testDomainList = testDtoList.map(VkServiceDto::toDomainObject)

        for ((dto, domain) in testDomainList.zip(testDomainList)) {
            assertEquals(dto.name, domain.name)
            assertEquals(dto.serviceUrl, domain.serviceUrl)
            assertEquals(dto.description, domain.description)
            assertEquals(dto.iconUrl, domain.iconUrl)
        }

        val testVkServicesListDto = VkServicesListDto(testDtoList)
        val testVkServicesList = VkServicesList(testDomainList)
        assertEquals(testVkServicesListDto.toDomainObject(), testVkServicesList)
    }

    @Test
    fun test_VkServiceRepositoryImpl_isCorrect() {
        testCoroutineScope.launch {
            val repoImpl1 = VkServiceRepositoryImpl(testApi1)
            assertEquals(listOf(testService), repoImpl1.getVkServices().items)
            val repoImpl2 = VkServiceRepositoryImpl(testApi2)
            assertEquals(VkServicesList(emptyList()), repoImpl2.getVkServices())
        }
    }

    @Test
    fun test_GetVkServicesListUseCase_isCorrect() {

        testCoroutineScope.launch {
            val testUseCase1 = GetVkServicesListUseCase(testRepo1)
            assertEquals(emptyArray<VkService>(), testUseCase1().items)

            val testUseCase2 = GetVkServicesListUseCase(testRepo2)
            assertEquals(listOf(testService), testUseCase2().items)
        }
    }
}