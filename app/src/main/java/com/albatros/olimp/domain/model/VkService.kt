package com.albatros.olimp.domain.model


import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class VkService(
    val description: String,
    val iconUrl: String,
    val name: String,
    val serviceUrl: String
): Parcelable