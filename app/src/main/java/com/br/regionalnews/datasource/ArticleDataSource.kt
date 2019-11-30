package com.br.regionalnews.datasource

import com.br.regionalnews.config.ApiResponse
import com.br.regionalnews.config.RetrofitInstance
import com.br.regionalnews.model.Article
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object ArticleDataSource {

    private val remote =  RetrofitInstance.articleService()

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

}