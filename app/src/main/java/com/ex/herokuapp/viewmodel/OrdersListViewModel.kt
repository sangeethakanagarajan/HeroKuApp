package com.ex.herokuapp.viewmodel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.ex.herokuapp.network.model.Orders
import com.ex.herokuapp.repository.OrdersListRepository

class OrdersListViewModel(application: Application) : AndroidViewModel(application) {

    private var isConnected: Boolean = false
    private val ordersListRepository = OrdersListRepository(application)

    val ordersList: LiveData<List<Orders>>

    init {
        this.ordersList = ordersListRepository.ordersList
    }

    fun getOrdersList() {
        ordersListRepository.getOrdersList()
    }


    fun isNetworkConnected(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val n = connectivityManager.activeNetwork
            if (n != null) {
                val nc = connectivityManager.getNetworkCapabilities(n)
                //It will check for both wifi and cellular network
                return nc!!.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) || nc.hasTransport(
                    NetworkCapabilities.TRANSPORT_WIFI
                )
            }
            return false
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            connectivityManager.registerDefaultNetworkCallback(object :
                ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: Network?) {
                    super.onAvailable(network)
                    isConnected = true
                }

                override fun onLost(network: Network?) {
                    super.onLost(network)
                    isConnected = false
                }
            })
            return isConnected
        } else {
            val netInfo = connectivityManager.activeNetworkInfo
            return netInfo != null && netInfo.isConnectedOrConnecting
        }
    }
}
