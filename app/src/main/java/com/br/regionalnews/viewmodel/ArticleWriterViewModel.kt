package com.br.regionalnews.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import com.br.regionalnews.config.SaveArticleRequest
import com.br.regionalnews.repository.ArticleRepository
import java.util.*

class ArticleWriterViewModel constructor(private val repository: ArticleRepository,
                                         application: Application): AndroidViewModel(application), LifecycleObserver {

    val loading = MutableLiveData<Boolean>().apply { value = false }
    val message = MutableLiveData<String>().apply { value = "" }

    private fun getArticleFrom(title: String, text: String, id: String?, isDraft: Boolean, isArchived: Boolean, image: String?): SaveArticleRequest {
        return SaveArticleRequest(
            "400a7465-93db-41aa-8d5f-7b87e3fd5c8d",
            "93dff14d-3d28-4591-bbea-3b8a6df1575a",
            "806b5c62-637e-454c-98c5-26a402520863",
            title,
            title,
            text,
            "2019-05-05T23:07:01.524Z",
            "",
            "Victor",
            Arrays.asList("oi", "tim"),
            "pt-BR",
            isDraft,
            true,
            id,
            isArchived,
            image)
    }

    fun editArticle(title: String, text: String, id: String?){
        loading.postValue(true)

        var article = this.getArticleFrom(title, text, id,false,false, null)

        repository.update(article, {
            loading.postValue(false)
            message.postValue("Notícia editada com sucesso!")
        }, {
            loading.postValue(false)
            message.postValue("Ocorreu um erro ao eeditar a notícia, por favor contacte o desenvolvedor!")
        })
    }

    fun saveArticle(image: String, title: String, text: String, id: String?){
        loading.postValue(true)

        var article = this.getArticleFrom(title, text, id,false,false, image)

        repository.save(article, {
            loading.postValue(false)
            message.postValue("Notícia criada com sucesso!")
        }, {
            loading.postValue(false)
            message.postValue("Ocorreu um erro ao criar a notícia, por favor contacte o desenvolvedor!")
        })
    }

    fun archiveArticle(title: String, text: String, id: String?){
        loading.postValue(true)

        var article = this.getArticleFrom(title, text, id,false,true, null)

        repository.update(article, {
            loading.postValue(false)
            message.postValue("Notícia arquivada com sucesso!")
        }, {
            loading.postValue(false)
            message.postValue("Ocorreu um erro ao arquivar a notícia, por favor contacte o desenvolvedor!")
        })
    }

    fun draftArticle(title: String, text: String, id: String?){
        loading.postValue(true)

        var article = this.getArticleFrom(title, text, id,true,false, null)

        repository.save(article, {
            loading.postValue(false)
            message.postValue("Rascunho de notícia salvo com sucesso!")
        }, {
            loading.postValue(false)
            message.postValue("Ocorreu um erro ao salvar rascunho de notícia, por favor contacte o desenvolvedor!")
        })
    }
}