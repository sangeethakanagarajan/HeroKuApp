package com.ex.herokuapp.network.model

import com.google.gson.annotations.SerializedName

data class OrdersList(
    @SerializedName("orders") val orders: List<Orders>
)