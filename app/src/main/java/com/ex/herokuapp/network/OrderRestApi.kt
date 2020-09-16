package com.ex.herokuapp.network

import com.ex.herokuapp.network.model.ArticlesList
import com.ex.herokuapp.network.model.OrdersList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface OrderRestApi {

    @GET("orders") // making get request at orderAPI end-point
    suspend fun getOrdersList(): Response<OrdersList>

    @GET("order-articles/{weborderid}") // making get request at orderAPI end-point
    suspend fun getArticlsList(@Path("weborderid") webOrderId: String): Response<ArticlesList>

}