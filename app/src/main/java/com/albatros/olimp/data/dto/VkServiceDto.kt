package com.albatros.olimp.data.dto

import com.albatros.olimp.domain.model.VkService
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class VkServiceDto(
    @SerializedName("description")
    @Expose
    val description: String,
    @SerializedName("icon_url")
    @Expose
    val iconUrl: String,
    @SerializedName("name")
    @Expose
    val name: String,
    @SerializedName("service_url")
    @Expose
    val serviceUrl: String
)

fun VkServiceDto.toDomainObject() = VkService(
    description = description,
    iconUrl = iconUrl,
    name = name,
    serviceUrl = serviceUrl
)