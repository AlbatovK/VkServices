package com.albatros.olimp.presentation.list

import com.albatros.olimp.domain.model.VkService

interface OnItemClickListener {
    fun onItemClick(service: VkService)
}