package com.br.regionalnews.view.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.br.regionalnews.R
import com.br.regionalnews.config.ApiResponse
import com.br.regionalnews.config.RetrofitInstance
import com.br.regionalnews.model.Article
import com.br.regionalnews.view.article.ArticleAdapter
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment: Fragment() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        getArticles()
        initButtons()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    private fun configureList(articles: ArrayList<Article>) {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = ArticleAdapter(articles)
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

    private fun getArticles() {

        val call = RetrofitInstance.articleService().listArticles()

        call.enqueue(object: Callback<ApiResponse?> {
            override fun onResponse(call: Call<ApiResponse?>, response: Response<ApiResponse?>?) {
                response?.body()?.let {

//                    Toast.makeText(context, "Requisição", Toast.LENGTH_SHORT).show()
                    val apiResponse: ApiResponse = it
                    configureList(apiResponse.data.list)

                }
            }

            override fun onFailure(call: Call<ApiResponse?>, t: Throwable) {
                Toast.makeText(context, "Ocorreu um erro! ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}