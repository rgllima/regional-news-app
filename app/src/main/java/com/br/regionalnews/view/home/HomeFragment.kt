package com.br.regionalnews.view.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.br.regionalnews.R
import com.br.regionalnews.model.Article
import com.br.regionalnews.view.article.ArticleAdapter
import kotlinx.android.synthetic.main.activity_articles_list.*

class HomeFragment: Fragment() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initAdapters()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.activity_articles_list, container, false)
    }

    private fun initAdapters() {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = ArticleAdapter(getArticles())
        }
    }

    private fun getArticles(): ArrayList<Article> {
        val dataList = ArrayList<Article>()
        for (i in 1..10){
            dataList.add(Article("Notícia #"+i, "Uma breve descrição da notícia#"+i))
        }

        return dataList
    }
}