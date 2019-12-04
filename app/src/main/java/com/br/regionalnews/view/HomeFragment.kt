package com.br.regionalnews.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.br.regionalnews.R
import com.br.regionalnews.config.ApiResponse
import com.br.regionalnews.config.RetrofitInstance
import com.br.regionalnews.factories.ArticleWriterViewModelFactory
import com.br.regionalnews.factories.HomeViewModelFactory
import com.br.regionalnews.model.Article
import com.br.regionalnews.repository.ArticleRepository
import com.br.regionalnews.view.article.ArticleAdapter
import com.br.regionalnews.view.article.ArticleHorizontalAdapter
import com.br.regionalnews.viewmodel.ArticleWriterViewModel
import com.br.regionalnews.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment: Fragment() {

    lateinit var viewModel: HomeViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initButtons()

        viewModel = createViewModel()

        viewModel.getArticles()

        viewModel.articles.observe(this, Observer {
            configureListVertical(viewModel.articles.value!!)
        })

        viewModel.articlesHorizontal.observe(this, Observer {
            configureListHorizontal(viewModel.articlesHorizontal.value!!)
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    private fun configureListVertical(articles: List<Article>) {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = ArticleAdapter(articles)
        }
    }

    private fun configureListHorizontal(articles: List<Article>) {
        recyclerViewHorizontal.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = ArticleHorizontalAdapter(articles)
        }
    }

    private fun initButtons() {
        profile_image.setOnClickListener {
            findNavController().navigate(R.id.actionGoSettings)
        }

        search_article.setOnClickListener{
            findNavController().navigate(R.id.actionOpenSearchDialog)
        }

        selected_city.setOnClickListener{
            findNavController().navigate(R.id.actionGoSelectCity)
        }
    }

    private fun createViewModel(): HomeViewModel {
        val factory = HomeViewModelFactory(ArticleRepository, activity?.application!!)

        return ViewModelProviders.of(this, factory).get(HomeViewModel::class.java)
    }
}