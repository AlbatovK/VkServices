package com.albatros.olimp.presentation.list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.albatros.olimp.R
import com.albatros.olimp.common.Resource
import com.albatros.olimp.databinding.FragmentListBinding
import com.albatros.olimp.domain.model.VkService
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragment : Fragment(R.layout.fragment_list), OnItemClickListener {

    private val viewModel: ListViewModel by viewModels()
    private var retrySnackbar: Snackbar? = null

    override fun onItemClick(service: VkService) {
        val direction = ListFragmentDirections.actionListFragmentToDetailFragment(service)
        findNavController().navigate(direction)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentListBinding.bind(view)
        val serviceAdapter = VkServiceAdapter(this)

        with(binding.serviceRecycler) {
            setHasFixedSize(true)
            adapter = serviceAdapter
            layoutManager = LinearLayoutManager(context)
        }

        viewModel.vkServices.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Error -> {
                    retrySnackbar?.dismiss()
                    retrySnackbar = Snackbar.make(
                        requireView(),
                        it.message ?: "Ошибка",
                        Snackbar.LENGTH_INDEFINITE
                    ).setAction("Ещё раз") {
                        viewModel.onRetryClicked()
                    }.also { snackbar ->
                        snackbar.show()
                    }
                }
                is Resource.Success -> {
                    serviceAdapter.submitList(it.data.items)
                }
                is Resource.Loading -> Unit
            }
        }
    }
}