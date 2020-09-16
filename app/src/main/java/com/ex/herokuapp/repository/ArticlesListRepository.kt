package com.ex.herokuapp.repository

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.ex.herokuapp.network.OrderRestApi
import com.ex.herokuapp.network.RestApiService
import com.ex.herokuapp.network.model.ArticlesList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class ArticlesListRepository(val application: Application) {

    val articlesList = MutableLiveData<ArticlesList>()

    fun getArticlesList(webOrderId: String) {
        //Network call
        val orderService = RestApiService.createService(OrderRestApi::class.java)

        CoroutineScope(Dispatchers.IO).launch {
            val response =
                orderService.getArticlsList(webOrderId)
            withContext(Dispatchers.Main) {
                try {
                    if (response.isSuccessful) {
                        //Do something with response e.g show to the UI.

                        var result = response.body()
                        if (result != null) {
                            articlesList.value = result
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