package com.br.regionalnews.datasource

import android.util.Log
import com.br.regionalnews.config.ApiResponse
import com.br.regionalnews.config.RetrofitInstance
import com.br.regionalnews.config.SaveArticleRequest
import com.br.regionalnews.model.Article
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object ArticleDataSource {

    private val remote =  RetrofitInstance.articleService()

    fun listLastArticles(success: (List<Article>) -> Unit, failure: () -> Unit) {
        val call = remote.listArticles()
        call.enqueue(object : Callback<ApiResponse> {

            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if (response.isSuccessful) {
                    val breeds = mutableListOf<Article>()
                    response.body()?.data?.list?.forEach {
                        breeds.add(it)
                    }

                    success(breeds)
                } else {
                    failure()
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable?) {
                failure()
            }
        })

    }

    fun listArticleByUser(success: (List<Article>) -> Unit, failure: () -> Unit) {
        val call = remote.getMyArticles()
        call.enqueue(object : Callback<ApiResponse> {

            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if (response.isSuccessful) {
                    val breeds = mutableListOf<Article>()
                    response.body()?.data?.list?.forEach {
                        breeds.add(it)
                    }

                    success(breeds)
                } else {
                    failure()
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable?) {
                failure()
            }
        })

    }

    fun save(article: SaveArticleRequest, success: (Article) -> Unit, failure: () -> Unit) {
        val call = remote.save(article)
        call.enqueue(object : Callback<Article> {

            override fun onResponse(call: Call<Article>, response: Response<Article>) {
                if (response.isSuccessful) {
                    success(response.body()!!)
                } else {
                    failure()
                }
            }

            override fun onFailure(call: Call<Article>, t: Throwable?) {
                failure()
            }
        })

    }

    fun update(article: SaveArticleRequest, success: (Article) -> Unit, failure: () -> Unit) {
        val call = remote.update(article)
        call.enqueue(object : Callback<Article> {

            override fun onResponse(call: Call<Article>, response: Response<Article>) {
                Log.d("TESTING", response.toString())
                if (response.isSuccessful) {
                    success(response.body()!!)
                } else {
                    failure()
                }
            }

            override fun onFailure(call: Call<Article>, t: Throwable?) {
                failure()
            }
        })

    }

}