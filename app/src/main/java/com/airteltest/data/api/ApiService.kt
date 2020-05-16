package com.airteltest.data.api

import SearchData
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("address/autocomplete")
    suspend fun getSearchAddressSuggestion(@Query("queryString") queryString: String, @Query("city") city: String): SearchData

}