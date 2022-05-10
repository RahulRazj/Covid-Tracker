package com.example.trackovid

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trackovid.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class WorldDataViewModel(private val repository: Repository): ViewModel() {

    val myResponse: MutableLiveData<Response<List<CountryDataItem>>> = MutableLiveData()


    fun getAllData() {
        viewModelScope.launch {
            val response: Response<List<CountryDataItem>> = repository.getAllData()
            myResponse.value = response
        }
    }
}