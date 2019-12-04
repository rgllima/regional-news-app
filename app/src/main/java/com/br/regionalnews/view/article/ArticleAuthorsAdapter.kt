package com.br.regionalnews.view.article

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.br.regionalnews.R
import com.br.regionalnews.model.Article
import androidx.navigation.findNavController
import com.br.regionalnews.view.ArticleWriterFragment
import com.github.debop.javatimes.NowLocalDateTime
import java.util.*


class ArticleAuthorsAdapter(val articleList: List<Article>) : RecyclerView.Adapter<ArticleAuthorsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val v = LayoutInflater.from(p0.context).inflate(R.layout.article_simple_item, p0, false)
        return ViewHolder(v, p0.context)
    }

    override fun getItemCount(): Int {
        return articleList.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {

        p0.name?.text = articleList[p1].title
        p0.description = articleList[p1].description
        p0.url = articleList[p1].url
        p0.imageUrl = articleList[p1].imageURL
        p0.simpleReading = articleList[p1].simpleReading
        p0._id = articleList[p1]._id
        p0.isArchived = articleList[p1].isArchived
        p0.date = articleList[p1].uploadedAt
        p0.isDraft = articleList[p1].isDraft
        p0.bind()
    }

    class ViewHolder(itemView: View, cont: Context) : RecyclerView.ViewHolder(itemView) {
        fun difDateFromNow(date: Date): String {
            var diff = Date().getTime() - date.getTime()
            var seconds = diff / 1000
            var minutes = seconds / 60
            var hours = minutes / 60
            var days = hours / 24

            if(days > 0) return "há "+days+" dias"
            else if(hours > 0) return "há "+hours+" horas"
            else if(minutes > 0) return "há "+hours+" horas"
            else if(seconds > 0) return "há "+seconds+" segundos"
            else return "há poucos instantes"
        }

        fun bind(){

            this.iconArchived.visibility = if(this.isArchived) View.VISIBLE else View.GONE
            this.iconDraft.visibility = if(this.isDraft) View.VISIBLE else View.GONE
            this.iconClock.visibility = if(!this.isArchived && !this.isDraft) View.VISIBLE else View.GONE

            if(this.isArchived) {
                this.status.setTextColor(Color.parseColor("#DD4B4B"))
                this.status.setText("Arquivado")
            } else if(this.isDraft){
                this.status.setTextColor(Color.parseColor("#1691E0"))
                this.status.setText("Rascunho")
            }else {
                this.iconClock.visibility = View.VISIBLE

                this.status.setTextColor(Color.parseColor("#7B7B7B"))
                this.status.setText(difDateFromNow(this.date))
            }


            itemView.setOnClickListener {
                val article = Article(this.name.text.toString(), this.description, this.url, this.imageUrl, this.simpleReading, this._id, this.isArchived, this.date, this.isDraft)
                itemView.findNavController().navigate(
                    R.id.actionGoArticleWriter,
                    ArticleWriterFragment.createArguments(article)
                )
            }

        }

        val iconArchived = itemView.findViewById<ImageView>(R.id.icon_archived)
        val iconClock = itemView.findViewById<ImageView>(R.id.icon_clock)
        val iconDraft = itemView.findViewById<ImageView>(R.id.icon_draft)
        val name = itemView.findViewById<TextView>(R.id.article_simple_title)
        val status = itemView.findViewById<TextView>(R.id.article_simple_status)

        var description: String = ""
        var url: String = ""
        var imageUrl: String? = null
        var simpleReading: Boolean = false
        var _id: String = ""
        var isArchived: Boolean = false
        var isDraft: Boolean = false
        var date: Date = Date()
        val context: Context = cont
    }
}