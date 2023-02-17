package com.albatros.olimp.presentation.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.albatros.olimp.common.Resource
import com.albatros.olimp.domain.model.VkServicesList
import com.albatros.olimp.domain.use_case.GetVkServicesListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val getVkServicesListUseCase: GetVkServicesListUseCase
) : ViewModel() {

    private val _vkServices = MutableLiveData<Resource<VkServicesList>>(Resource.Loading()).apply {
        viewModelScope.launch(Dispatchers.Main) {
            value = loadServices()
        }
    }

    val vkServices: LiveData<Resource<VkServicesList>> = _vkServices

    private suspend fun loadServices() = try {
        val services = getVkServicesListUseCase()
        Resource.Success(services)
    } catch (e: HttpException) {
        Resource.Error("Ошибка запроса")
    } catch (e: IOException) {
        Resource.Error("Отсутствует подключение")
    }

    fun onRetryClicked() = viewModelScope.launch(Dispatchers.Main) {
        _vkServices.value = loadServices()
    }
}