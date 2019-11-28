package com.br.regionalnews.view.articleauthorslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.br.regionalnews.R
import kotlinx.android.synthetic.main.fragment_article_author_list.*

class ArticleAuthorsListFragment : Fragment() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        addArticle.setOnClickListener {
            findNavController().navigate(R.id.actionGoArticleWriter)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_article_author_list, container, false)
    }
}