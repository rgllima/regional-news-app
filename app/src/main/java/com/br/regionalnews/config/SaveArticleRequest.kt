package com.br.regionalnews.config

data class SaveArticleRequest(
    val _source: String,
    val _city: String,
    val _microregion: String,
    val headline: String,
    val url: String,
    val description: String,
    val publishedAt: String,
    val imageURL: String,
    val author: String,
    val keywords: List<String>,
    val lang: String,
    val isDraft: Boolean,
    val simpleReading: Boolean,
    val _id: String?,
    val isArchived: Boolean,
    val image: String?) {



}
