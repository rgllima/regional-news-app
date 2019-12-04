package com.br.regionalnews.repository

import com.br.regionalnews.config.SaveArticleRequest
import com.br.regionalnews.datasource.ArticleDataSource
import com.br.regionalnews.model.Article

object ArticleRepository {
    private val dataSource = ArticleDataSource

    fun listAll(success: (List<Article>) -> Unit, failure: () -> Unit) {
        dataSource.listLastArticles({
            success(it)
        }, {
            dataSource.listLastArticles(success, failure)
        })
    }

    fun listAllByUser(success: (List<Article>) -> Unit, failure: () -> Unit) {
        dataSource.listArticleByUser({
            success(it)
        }, {
            dataSource.listArticleByUser(success, failure)
        })
    }

    fun save(article: SaveArticleRequest, success: (Article) -> Unit, failure: () -> Unit){
        dataSource.save(article, {
            success(it)
        }, {
            dataSource.save(article, success, failure)
        })
    }

    fun update(article: SaveArticleRequest, success: (Article) -> Unit, failure: () -> Unit){
        dataSource.update(article, {
            success(it)
        }, {
            dataSource.update(article, success, failure)
        })
    }


}