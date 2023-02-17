package com.albatros.olimp.data.dto

import com.albatros.olimp.domain.model.VkServicesList
import com.google.gson.annotations.SerializedName

data class VkServicesListDto(
    @SerializedName("items")
    val items: List<VkServiceDto>
)

fun VkServicesListDto.toDomainObject() = VkServicesList(
    items = items.map(VkServiceDto::toDomainObject)
)