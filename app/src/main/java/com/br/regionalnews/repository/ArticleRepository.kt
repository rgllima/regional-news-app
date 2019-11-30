package com.br.regionalnews.repository

import com.br.regionalnews.datasource.ArticleDataSource
import com.br.regionalnews.model.Article

object ArticleRepository {
    private val dataSource = ArticleDataSource

    fun listAll(success: (List<Article>) -> Unit, failure: () -> Unit) {
        dataSource.listArticleByUser({
            success(it)
        }, {
            dataSource.listArticleByUser(success, failure)
        })
    }


}