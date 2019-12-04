package com.br.regionalnews.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import com.br.regionalnews.model.Article
import com.br.regionalnews.repository.ArticleRepository
import java.util.stream.Collectors

class HomeViewModel constructor(private val repository: ArticleRepository,
                                application: Application): AndroidViewModel(application), LifecycleObserver {

    val articles = MutableLiveData<List<Article>>().apply { value = emptyList() }
    val articlesHorizontal = MutableLiveData<List<Article>>().apply { value = emptyList() }

    fun getArticles(){
        repository.listAll({ items ->
            articlesHorizontal.value = (items.filter { it.simpleReading })
            articles.value = (items.filter { !it.simpleReading })
        }, {
            Log.d("ArticleAuthorsViewModel", "Erro ao carregar itens...")
        })
    }
}