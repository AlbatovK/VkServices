package com.albatros.olimp.domain.model


import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class VkService(
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
): Parcelable