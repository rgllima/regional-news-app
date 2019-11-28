package com.br.regionalnews.services

import com.br.regionalnews.config.ApiResponse
import com.br.regionalnews.config.SaveArticleRequest
import com.br.regionalnews.model.Article
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT

interface ArticleService {
    @GET("news/last-news/page/1")
    fun listArticles(): Call<ApiResponse>

    @GET("news/get-by-source/400a7465-93db-41aa-8d5f-7b87e3fd5c8d/page/1")
    fun getMyArticles(): Call<ApiResponse>

    @POST("news/create")
    fun crate(@Body article: SaveArticleRequest): Call<Article>

    @POST("news/update")
    fun update(@Body article: SaveArticleRequest): Call<Article>
}