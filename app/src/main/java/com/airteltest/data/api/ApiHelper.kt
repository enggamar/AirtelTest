package com.airteltest.data.api

class ApiHelper(private val apiService: ApiService) {

    suspend fun getSearchAddresses(queryString: String, city: String) = apiService.getSearchAddressSuggestion(queryString, city)
}