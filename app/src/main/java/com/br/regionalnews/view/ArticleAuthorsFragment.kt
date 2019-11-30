package com.br.regionalnews.view.articleauthorslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.br.regionalnews.R
import com.br.regionalnews.config.ApiResponse
import com.br.regionalnews.config.RetrofitInstance
import com.br.regionalnews.model.Article
import com.br.regionalnews.view.article.ArticleAuthorsAdapter
import kotlinx.android.synthetic.main.fragment_article_author_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ArticleAuthorsListFragment : Fragment() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)



        addArticle.setOnClickListener {
            findNavController().navigate(R.id.actionGoArticleWriter)
        }

        getArticles()


    }

    private fun configureList(articles: ArrayList<Article>) {
        recyclerViewAuthor.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = ArticleAuthorsAdapter(articles)
        }
    }
    private fun getArticles() {

        val call = RetrofitInstance.articleService().getMyArticles()

        call.enqueue(object: Callback<ApiResponse?> {
            override fun onResponse(call: Call<ApiResponse?>, response: Response<ApiResponse?>?) {
                response?.body()?.let {
                    val apiResponse: ApiResponse = it
                    configureList(apiResponse.data.list)

                }
            }

            override fun onFailure(call: Call<ApiResponse?>, t: Throwable) {
                Toast.makeText(context, "Ocorreu um erro! ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_article_author_list, container, false)
    }
}