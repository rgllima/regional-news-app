package com.br.regionalnews.config

import com.br.regionalnews.model.Article
import com.google.gson.annotations.SerializedName

data class ApiArticleIndexer(
    @SerializedName("results") val results: String,
    @SerializedName("page") val page: Int,
//    @SerializedName("message") val message: String,
    @SerializedName("data") val list: ArrayList<Article>
)