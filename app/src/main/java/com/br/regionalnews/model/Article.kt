package com.br.regionalnews.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*

data class Article (
    @SerializedName("headline") val title: String,
    @SerializedName("description") val description: String,
    @SerializedName("url") val url: String,
    @SerializedName("imageURL") val imageURL: String? = null,
    @SerializedName("simpleReading") val simpleReading: Boolean,
    @SerializedName("_id") val _id: String,
    @SerializedName("isArchived") val isArchived: Boolean,
    @SerializedName("uploadedAt") val uploadedAt: Date,
    @SerializedName("isDraft") val isDraft: Boolean
) : Serializable