package com.albatros.olimp.domain.module

import com.albatros.olimp.domain.repo.VkServiceRepositoryImpl
import com.albatros.olimp.data.repo.VkServiceRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindVkServiceRepository(
        vkServiceRepositoryImpl: VkServiceRepositoryImpl
    ): VkServiceRepository
}