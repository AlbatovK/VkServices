package com.albatros.olimp.domain.model


import com.albatros.olimp.domain.model.VkService
import com.google.gson.annotations.SerializedName

data class VkServicesList(
    @SerializedName("items")
    val items: List<VkService>
)