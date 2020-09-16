package com.ex.herokuapp.network.model

import com.google.gson.annotations.SerializedName

data class ArticlesList(

    @SerializedName("status") val status: Boolean,
    @SerializedName("data") val data: List<Data>
)