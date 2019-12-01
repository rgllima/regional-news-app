package com.br.regionalnews.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import com.br.regionalnews.R
import com.br.regionalnews.config.RetrofitInstance
import com.br.regionalnews.config.SaveArticleRequest
import com.br.regionalnews.model.Article
import com.br.regionalnews.view.articlewebview.ArticleWebviewFragment
import kotlinx.android.synthetic.main.fragment_article_write.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class ArticleWriterFragment : Fragment() {

    val article: MutableLiveData<Article> = MutableLiveData()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        loadArguments(arguments)
        initButtons()

        article.observe(this, androidx.lifecycle.Observer {
            if(article.value != null){
                this.initButtons()
                this.updateValues();

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

        if(this.article.value == null){
            articleDraftBtn.visibility = View.VISIBLE

            articlePublishBtn.setOnClickListener {
                var titulo = articleTitle
                var texto = articleText

                val req = SaveArticleRequest("400a7465-93db-41aa-8d5f-7b87e3fd5c8d", "93dff14d-3d28-4591-bbea-3b8a6df1575a", "806b5c62-637e-454c-98c5-26a402520863", titulo.text.toString()
                    , titulo.text.toString(), texto.text.toString(), "2019-05-05T23:07:01.524Z", "", "Victor", Arrays.asList("oi", "tim"), "pt-BR", false, true, article.value?._id)

                RetrofitInstance.articleService().crate(req).enqueue(object: Callback<Article?> {
                    override fun onResponse(call: Call<Article?>, response: Response<Article?>?) {
                        response?.body()?.let {

                            Toast.makeText(context, "SALVO HAHA", Toast.LENGTH_SHORT).show()


                        }
                    }

                    override fun onFailure(call: Call<Article?>, t: Throwable) {
                        Toast.makeText(context, "Ocorreu um erro! ${t.message}", Toast.LENGTH_SHORT).show()
                    }
                })
            }
        }else {
            articleDraftBtn.visibility = View.INVISIBLE
            articlePublishBtn.setText("Editar")

            articlePublishBtn.setOnClickListener {
                val req = SaveArticleRequest("400a7465-93db-41aa-8d5f-7b87e3fd5c8d", "93dff14d-3d28-4591-bbea-3b8a6df1575a", "806b5c62-637e-454c-98c5-26a402520863", articleTitle.text.toString()
                    , articleTitle.text.toString(), articleText.text.toString(), "2019-05-05T23:07:01.524Z", "", "Victor", Arrays.asList("oi", "tim"), "pt-BR", false, true, article.value?._id)

                RetrofitInstance.articleService().update(req).enqueue(object: Callback<Article?> {
                    override fun onResponse(call: Call<Article?>, response: Response<Article?>?) {
                        response?.body()?.let {

                            Toast.makeText(context, "EDITADO", Toast.LENGTH_SHORT).show()


                        }
                    }

                    override fun onFailure(call: Call<Article?>, t: Throwable) {
                        Toast.makeText(context, "Ocorreu um erro! ${t.message}", Toast.LENGTH_SHORT).show()
                    }
                })
            }
        }



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