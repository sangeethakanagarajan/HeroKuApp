package com.ex.herokuapp.repository

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.ex.herokuapp.network.OrderRestApi
import com.ex.herokuapp.network.RestApiService
import com.ex.herokuapp.network.model.Orders
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class OrdersListRepository(val application: Application) {

    val ordersList = MutableLiveData<List<Orders>>()

    fun getOrdersList() {
        //Network call
        val orderService = RestApiService.createService(OrderRestApi::class.java)

        CoroutineScope(Dispatchers.IO).launch {
            val response =
                orderService.getOrdersList()
            withContext(Dispatchers.Main) {
                try {
                    if (response.isSuccessful) {
                        //Do something with response e.g show to the UI.

                        var result = response.body()
                        if (result != null) {
                            ordersList.value = result.orders
                        }
                    } else {
                        toast("Error: ${response.code()}")
                    }
                } catch (e: HttpException) {
                    toast("Exception ${e.message}")
                } catch (e: Throwable) {
                    toast("Ooops: Something else went wrong")
                }
            }
        }
    }

    private fun toast(s: String) {
        Toast.makeText(application, s, Toast.LENGTH_SHORT).show()
    }
}