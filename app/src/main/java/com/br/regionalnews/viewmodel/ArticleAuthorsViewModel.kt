package com.br.regionalnews.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.br.regionalnews.model.Article
import com.br.regionalnews.repository.ArticleRepository

class ArticleAuthorsViewModel constructor(private val repository: ArticleRepository,
                                          application: Application): AndroidViewModel(application), LifecycleObserver {

    val articles = MutableLiveData<List<Article>>().apply { value = emptyList()}



    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun load() {
        repository.listAll({ items ->
            articles.value = (items)
        }, {
            Log.d("ArticleAuthorsViewModel", "Erro ao carregar itens...")
        })

    }


}