package com.br.regionalnews.services

import com.br.regionalnews.config.ApiResponse
import retrofit2.Call
import retrofit2.http.GET

interface ArticleService {
    @GET("news/last-news/page/1")
    fun listArticles(): Call<ApiResponse>
}