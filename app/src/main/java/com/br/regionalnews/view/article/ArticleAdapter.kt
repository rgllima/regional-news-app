package com.br.regionalnews.view.article

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.br.regionalnews.R
import com.br.regionalnews.model.Article
import androidx.navigation.findNavController
import com.br.regionalnews.view.articlewebview.ArticleWebviewFragment
import java.util.*
import kotlin.collections.ArrayList


class ArticleAdapter(val articleList: List<Article>) : RecyclerView.Adapter<ArticleAdapter.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val v = LayoutInflater.from(p0.context).inflate(R.layout.article_item, p0, false)
        return ViewHolder(v, p0.context)
    }

    override fun getItemCount(): Int {
        return articleList.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {

        p0.name?.text = articleList[p1].title
        p0.description?.text = articleList[p1].description
        p0.url = articleList[p1].url
        p0.imageUrl = articleList[p1].imageURL
        p0.simpleReading = articleList[p1].simpleReading
        p0.id = articleList[p1]._id
        p0.date = articleList[p1].uploadedAt
        p0.isDraft = articleList[p1].isDraft
        p0.bind(p0.context)
    }

    class ViewHolder(itemView: View, cont: Context) : RecyclerView.ViewHolder(itemView) {
        fun bind(context: Context){

            itemView.setOnClickListener {

                val article = Article(this.name.text.toString(), this.description.text.toString(), this.url, this.imageUrl, this.simpleReading, this.id, false, this.date, this.isDraft)
                itemView.findNavController().navigate(
                    R.id.actionGoArticleWebView,
                    ArticleWebviewFragment.createArguments(article)
                )
            }

        }

        val name = itemView.findViewById<TextView>(R.id.articleTitle)
        val description = itemView.findViewById<TextView>(R.id.articleDescription)

        var url: String = ""
        var imageUrl: String? = null
        var simpleReading: Boolean = false;
        var id: String = ""
        var date: Date = Date()
        var isDraft: Boolean = false
        val context: Context = cont
    }
}