package com.br.regionalnews.factories

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.br.regionalnews.repository.ArticleRepository
import com.br.regionalnews.viewmodel.ArticleAuthorsViewModel
import com.br.regionalnews.viewmodel.ArticleWriterViewModel

class ArticleWriterViewModelFactory constructor(private val repository: ArticleRepository,
                                                 private val application: Application): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        with(modelClass){
            when {
                isAssignableFrom(ArticleWriterViewModel::class.java) ->
                    ArticleWriterViewModel(repository, application)
                else ->
                    throw IllegalArgumentException("Classe desconhecida.")
            }
        } as T
}