package com.br.regionalnews.config

import com.br.regionalnews.services.ArticleService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private const val BASE_URL: String = "https://regional-news-api.herokuapp.com"

    private val retrofit = Retrofit.Builder()
        .baseUrl(this.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun articleService(): ArticleService {
        return retrofit.create(ArticleService::class.java)
    }
}