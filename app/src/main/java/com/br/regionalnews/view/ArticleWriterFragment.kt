package com.br.regionalnews.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.br.regionalnews.R
import com.br.regionalnews.config.RetrofitInstance
import com.br.regionalnews.config.SaveArticleRequest
import com.br.regionalnews.factories.ArticleAuthorsViewModelFactory
import com.br.regionalnews.factories.ArticleWriterViewModelFactory
import com.br.regionalnews.model.Article
import com.br.regionalnews.repository.ArticleRepository
import com.br.regionalnews.view.articlewebview.ArticleWebviewFragment
import com.br.regionalnews.viewmodel.ArticleAuthorsViewModel
import com.br.regionalnews.viewmodel.ArticleWriterViewModel
import kotlinx.android.synthetic.main.fragment_article_write.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class ArticleWriterFragment : Fragment() {

    lateinit var viewModel: ArticleWriterViewModel

    val article: MutableLiveData<Article> = MutableLiveData()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        loadArguments(arguments)
        initButtons()
        viewModel = createViewModel()

        article.observe(this, androidx.lifecycle.Observer {
            if(article.value != null){
                this.initButtons()
                this.updateValues()

            }
        })


        viewModel.loading.observe(this, androidx.lifecycle.Observer {
            if(it){
                loadingWriteFrag.visibility = View.VISIBLE
            }else {
                loadingWriteFrag.visibility = View.GONE
            }
        })

        viewModel.message.observe(this, androidx.lifecycle.Observer {
            if(!it.isEmpty()){
                Toast.makeText(context, it, Toast.LENGTH_LONG).show()
            }
        })
    }

    fun updateValues(){
        articleTitle.setText(article.value?.title)
        articleText.setText(article.value?.description)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_article_write, container, false)
    }

    fun initButtons(){
        article_publish_back_btn.setOnClickListener {
            findNavController().popBackStack()
        }

        articleArchiveBtn.setOnClickListener {
            viewModel.archiveArticle(article.value!!.title, article.value!!.description, article.value?._id)
        }

        articleDraftBtn.setOnClickListener {
            var title = articleTitle.text.toString()
            var text = articleText.text.toString()
            viewModel.draftArticle(title, text, article.value?._id)
        }

        articleDraftBtn.visibility = if (this.article.value == null) View.VISIBLE else View.GONE
        articleArchiveBtn.visibility = if (article.value?.isArchived == false) View.VISIBLE else View.GONE

        if(this.article.value == null){
            articlePublishBtn.setOnClickListener {
                var title = articleTitle.text.toString()
                var text = articleText.text.toString()

                viewModel.saveArticle(title, text, "")
            }
        }else {
            if(!article.value!!.isArchived) {
                articlePublishBtn.setText("Editar")
            }else {
                articlePublishBtn.setText("Editar e desarquivar")
            }

            articlePublishBtn.setOnClickListener {
                var title = articleTitle.text.toString()
                var text = articleText.text.toString()

                viewModel.editArticle(title, text, article.value?._id)
            }

        }



    }

    private fun createViewModel(): ArticleWriterViewModel {
        val factory = ArticleWriterViewModelFactory(ArticleRepository, activity?.application!!)

        return ViewModelProviders.of(this, factory).get(ArticleWriterViewModel::class.java)
    }

    companion object {
        private const val ARTICLE_ARGUMENT = "article"

        fun createArguments(article: Article): Bundle {
            val bundle = Bundle()
            bundle.putSerializable(ARTICLE_ARGUMENT, article)

            return bundle
        }

        // Isso deve ficar nessa classe
        fun newInstance(): ArticleWebviewFragment {
            return ArticleWebviewFragment()
        }
    }

    fun loadArguments(arguments: Bundle?) {
        if (arguments == null) {
            return
        }

        val article: Article? = arguments.get(ARTICLE_ARGUMENT) as Article
        this.article.postValue(article)
    }
}