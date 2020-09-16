package com.ex.herokuapp.network.model

import com.google.gson.annotations.SerializedName

data class Orders(
    @SerializedName("webOrderId") val webOrderId: String,
    @SerializedName("totalOrderQty") val totalOrderQty: Int,
    @SerializedName("sapBlacklistTime") val sapBlacklistTime: String
    /*  @SerializedName("siteId") val siteId : String,
      @SerializedName("lockStatus") val lockStatus : Boolean,
      @SerializedName("sapOrderTime") val sapOrderTime : String,
      @SerializedName("orderType") val orderType : String,
      @SerializedName("customerName") val customerName : String,
      @SerializedName("paymentStatus") val paymentStatus : Int,
      @SerializedName("putAwayLocation") val putAwayLocation : String,
      @SerializedName("orderStatus") val orderStatus : String,
      @SerializedName("returnFlag") val returnFlag : Boolean,
      @SerializedName("lastUpdatedUser") val lastUpdatedUser : String,
      @SerializedName("blockCollection") val blockCollection : Boolean,
      @SerializedName("pickedDateTime") val pickedDateTime : String,
      @SerializedName("qtyPicked") val qtyPicked : Int,
      @SerializedName("pickedDeviceId") val pickedDeviceId : String*/
)