package com.ex.herokuapp.network.model

import com.google.gson.annotations.SerializedName


data class Data(

    @SerializedName("webOrderId") val webOrderId: String,
    @SerializedName("blackListTime") val blackListTime: String,
    @SerializedName("articleId") val articleId: String,
    @SerializedName("article") val article: Article,
    @SerializedName("orderQty") val orderQty: Int,
    @SerializedName("qtyPicked") val qtyPicked: Int,
    @SerializedName("highVolumeFlag") val highVolumeFlag: Boolean,
    @SerializedName("pickedDateTime") val pickedDateTime: String?,
    @SerializedName("consoleSerialNo") val consoleSerialNo: String?,
    @SerializedName("pickedBy") val pickedBy: String?,
    @SerializedName("pickedDeviceId") val pickedDeviceId: String?,
    @SerializedName("articleStatus") val articleStatus: String,
    @SerializedName("articleStockQty") val articleStockQty: Int
)