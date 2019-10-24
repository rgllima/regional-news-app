package com.br.regionalnews

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.viewpager.widget.ViewPager
import com.br.regionalnews.model.Article
import com.br.regionalnews.view.article.ArticleAdapter
import com.br.regionalnews.view.configuration.ConfigurationActivity
import com.br.regionalnews.view.initial.InitialPageAdapter
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_articles_list.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_articles_list)

        val recycleView = recyclerView


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

        return dataList;
    }
}
