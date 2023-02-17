package com.albatros.olimp.domain.module

import com.albatros.olimp.common.Constants.base_url
import com.albatros.olimp.data.remote.VkApiClient
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideGsonFactory(gson: Gson): GsonConverterFactory = GsonConverterFactory.create(gson)

    @Provides
    @Singleton
    fun provideVkApiClient(retrofit: Retrofit): VkApiClient =
        retrofit.create(VkApiClient::class.java)

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
        .setPrettyPrinting()
        .serializeNulls()
        .create()

    @Provides
    @Singleton
    fun provideRetrofit(factory: GsonConverterFactory): Retrofit =
        Retrofit.Builder()
            .baseUrl(base_url)
            .addConverterFactory(factory)
            .build()
}