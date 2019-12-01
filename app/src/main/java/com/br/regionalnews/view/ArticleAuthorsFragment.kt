package com.br.regionalnews.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.br.regionalnews.R
import com.br.regionalnews.factories.ArticleAuthorsViewModelFactory
import com.br.regionalnews.model.Article
import com.br.regionalnews.repository.ArticleRepository
import com.br.regionalnews.view.article.ArticleAuthorsAdapter
import com.br.regionalnews.viewmodel.ArticleAuthorsViewModel
import kotlinx.android.synthetic.main.fragment_article_author_list.*
import org.jetbrains.annotations.NotNull

class ArticleAuthorsFragment : Fragment() {

    lateinit var viewModel: ArticleAuthorsViewModel

    override fun onCreateView(@NotNull inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        viewModel = createViewModel()
        lifecycle.addObserver(viewModel)

        viewModel.articles.observe(this, Observer {
            initRecyclerView(it)
        })

        return inflater.inflate(R.layout.fragment_article_author_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initButtons()
    }

    private fun initButtons(){
        addArticle.setOnClickListener {
            findNavController().navigate(R.id.actionGoArticleWriter)
        }
    }

    private fun initRecyclerView(articles: List<Article>) {
        recyclerViewAuthor.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = ArticleAuthorsAdapter(articles)
        }
    }

    private fun createViewModel(): ArticleAuthorsViewModel {
        val factory = ArticleAuthorsViewModelFactory(ArticleRepository, activity?.application!!)

        return ViewModelProviders.of(this, factory).get(ArticleAuthorsViewModel::class.java)
    }
}