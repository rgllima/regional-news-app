package com.br.regionalnews.factories

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.br.regionalnews.repository.ArticleRepository
import com.br.regionalnews.viewmodel.HomeViewModel

class HomeViewModelFactory constructor(private val repository: ArticleRepository,
                                                private val application: Application
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        with(modelClass){
            when {
                isAssignableFrom(HomeViewModel::class.java) ->
                    HomeViewModel(repository, application)
                else ->
                    throw IllegalArgumentException("Classe desconhecida.")
            }
        } as T
}