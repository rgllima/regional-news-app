package com.br.regionalnews.view.article

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.br.regionalnews.R
import com.br.regionalnews.model.Article
import kotlinx.android.synthetic.main.activity_articles_list.*

class ArticlesListActivity : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_articles_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dataList = ArrayList<Article>()
        dataList.add(Article("Muito legal", "Uma breve descrição da notícia"))
        dataList.add(Article("Muito legal", "Uma breve descrição da notícia"))
        dataList.add(Article("Muito legal", "Uma breve descrição da notícia"))
        dataList.add(Article("Muito legal", "Uma breve descrição da notícia"))
        dataList.add(Article("Muito legal", "Uma breve descrição da notícia"))
        dataList.add(Article("Muito legal", "Uma breve descrição da notícia"))
        dataList.add(Article("Muito legal", "Uma breve descrição da notícia"))
        dataList.add(Article("Muito legal", "Uma breve descrição da notícia"))
        dataList.add(Article("Muito legal", "Uma breve descrição da notícia"))
        dataList.add(Article("Muito legal", "Uma breve descrição da notícia"))
        dataList.add(Article("Muito legal", "Uma breve descrição da notícia"))
        dataList.add(Article("Muito legal", "Uma breve descrição da notícia"))
        dataList.add(Article("Muito legal", "Uma breve descrição da notícia"))
        dataList.add(Article("Muito legal", "Uma breve descrição da notícia"))
        dataList.add(Article("Muito legal", "Uma breve descrição da notícia"))
        dataList.add(Article("Muito legal", "Uma breve descrição da notícia"))

        val rvAdapter = ArticleAdapter(dataList)


        recyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = rvAdapter
        }
    }
}
