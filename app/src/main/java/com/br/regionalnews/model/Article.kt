package com.br.regionalnews.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Article (
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String
) : Serializable