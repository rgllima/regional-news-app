package com.br.regionalnews.view.article

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.br.regionalnews.R
import com.br.regionalnews.model.Article
import android.media.MediaPlayer
import android.media.MediaPlayer.OnCompletionListener



class ArticleAdapter(val articleList: ArrayList<Article>) : RecyclerView.Adapter<ArticleAdapter.ViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val v = LayoutInflater.from(p0?.context).inflate(com.br.regionalnews.R.layout.article_item, p0, false)
        return ViewHolder(v, p0?.context)
    }
    override fun getItemCount(): Int {
        return articleList.size
    }
    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {

        p0.name?.text = articleList[p1].title
        p0.description?.text = articleList[p1].description
        p0.bind(p0.context)
    }
    class ViewHolder(itemView: View, cont: Context) : RecyclerView.ViewHolder(itemView) {
        fun bind(context: Context){
            itemView.setOnLongClickListener(View.OnLongClickListener {
                val mp = MediaPlayer.create(context, R.raw.bell)
                mp.setOnCompletionListener(OnCompletionListener { mp -> mp.release() })
                mp.start()

                itemView.setBackgroundColor(itemView.resources.getColor(R.color.colorPrimaryDark, context.theme))

                true
            })
        }
        val name = itemView.findViewById<TextView>(R.id.articleTitle)
        val description = itemView.findViewById<TextView>(R.id.articleDescription)
        val context: Context = cont


    }
}