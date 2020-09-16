package com.ex.herokuapp.network.model

import com.google.gson.annotations.SerializedName


data class Article(

    @SerializedName("articleId") val articleId: String,
    @SerializedName("articleDesc") val articleDesc: String,
    @SerializedName("articleHierNode") val articleHierNode: String,
    @SerializedName("articleGroup") val articleGroup: String,
    @SerializedName("image") val image: String?,
    @SerializedName("serialScan") val serialScan: Boolean,
    @SerializedName("articleBarcode") val articleBarcode: String,
    @SerializedName("articleNodePriority") val articleNodePriority: Int,
    @SerializedName("deptDesc") val deptDesc: String
)