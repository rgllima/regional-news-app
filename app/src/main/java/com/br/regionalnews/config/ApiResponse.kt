package com.br.regionalnews.config

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ApiResponse (
    @SerializedName("message") val message: String,
    @SerializedName("data") val data: ApiArticleIndexer
) : Serializable