package com.example.trackovid.repository

import com.example.trackovid.CountryDataItem
import com.example.trackovid.api.RetrofitInstance
import retrofit2.Response

class Repository {

    suspend fun getData(countryName: String): Response<CountryDataItem> {
        return RetrofitInstance.api.getData(countryName)
    }

    suspend fun getAllData(): Response<List<CountryDataItem>> {
        return RetrofitInstance.api.getAllData()
    }
}