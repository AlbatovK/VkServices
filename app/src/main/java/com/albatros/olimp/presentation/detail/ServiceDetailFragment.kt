package com.albatros.olimp.presentation.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import coil.load
import com.albatros.olimp.presentation.MainActivity
import com.albatros.olimp.R
import com.albatros.olimp.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ServiceDetailFragment : Fragment(R.layout.fragment_detail) {

    private val arg by navArgs<ServiceDetailFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentDetailBinding.bind(view)
        val service = arg.arg
        (activity as MainActivity).supportActionBar?.title = service.name

        with(binding) {
            icon.load(service.iconUrl) {
                placeholder(R.drawable.ic_warning_icon)
            }
            nameTxt.text = service.name
            descrTxt.text = service.description
            linkTxt.text = service.serviceUrl
        }
    }
}