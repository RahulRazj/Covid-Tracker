package com.example.trackovid.api

import com.example.trackovid.CountryDataItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {

    @GET("countries/{countryName}")
    suspend fun getData(@Path("countryName") countryName: String): Response<CountryDataItem>

    @GET("countries")
    suspend fun getAllData(): Response<List<CountryDataItem>>
}