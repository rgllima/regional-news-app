package com.br.regionalnews.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Article (
    @SerializedName("headline") val title: String,
    @SerializedName("description") val description: String,
    @SerializedName("url") val url: String,
    @SerializedName("imageURL") val imageURL: String? = null,
    @SerializedName("simpleReading") val simpleReading: Boolean,
    @SerializedName("_id") val _id: String
) : Serializable