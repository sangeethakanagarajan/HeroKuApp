package com.ex.herokuapp.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RestApiService {

    const val API_BASE_URL = "https://zakapps-test.herokuapp.com/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(API_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun <S> createService(serviceClass: Class<S>?): S {
        return retrofit.create(serviceClass)
    }
}
