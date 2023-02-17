package com.albatros.olimp.presentation.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.albatros.olimp.R
import com.albatros.olimp.databinding.ServiceCardLayoutBinding
import com.albatros.olimp.domain.model.VkService

class VkServiceAdapter(
    val listener: OnItemClickListener
) : ListAdapter<VkService, VkServiceAdapter.ServiceViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServiceViewHolder {
        val binding = ServiceCardLayoutBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ServiceViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ServiceViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    inner class ServiceViewHolder(private val binding: ServiceCardLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(service: VkService) {
            with(binding) {
                name.text = service.name
                icon.load(service.iconUrl) {
                    placeholder(R.drawable.ic_warning_icon)
                }
                root.setOnClickListener {
                    listener.onItemClick(service)
                }
            }
        }
    }

    companion object {
        val diffCallback = DiffCallback()
    }

    class DiffCallback : DiffUtil.ItemCallback<VkService>() {

        override fun areItemsTheSame(
            oldItem: VkService,
            newItem: VkService
        ) = oldItem.name == newItem.name

        override fun areContentsTheSame(
            oldItem: VkService,
            newItem: VkService
        ) = oldItem == newItem
    }
}