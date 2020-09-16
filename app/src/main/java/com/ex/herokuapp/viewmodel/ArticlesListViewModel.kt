package com.ex.herokuapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.ex.herokuapp.network.model.ArticlesList
import com.ex.herokuapp.repository.ArticlesListRepository

class ArticlesListViewModel(application: Application) : AndroidViewModel(application) {

    private val articlesListRepository = ArticlesListRepository(application)
    val articlesList: LiveData<ArticlesList>

    init {
        this.articlesList = articlesListRepository.articlesList
    }

    fun getArticlesList(webOrderId: String) {
        articlesListRepository.getArticlesList(webOrderId)
    }

}
