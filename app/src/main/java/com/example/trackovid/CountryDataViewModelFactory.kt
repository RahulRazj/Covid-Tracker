package com.example.trackovid

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.trackovid.repository.Repository

class CountryDataViewModelFactory(
    private val repository: Repository
    ): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CountryDataViewModel(repository) as T
    }
}