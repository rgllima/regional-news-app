package com.br.regionalnews.view.articlewebview

import android.content.DialogInterface
import android.graphics.Rect
import android.os.Bundle
import android.view.*
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.br.regionalnews.R
import com.br.regionalnews.model.Article
import kotlinx.android.synthetic.main.article_webview_fragment.*

class ArticleWebviewFragment: DialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val displayReact = Rect()
        val window = dialog?.window

        window?.setGravity(Gravity.TOP)
        window?.setBackgroundDrawableResource(android.R.color.white)
        window?.decorView?.getWindowVisibleDisplayFrame(displayReact)
        window?.setLayout(displayReact.width(), WindowManager.LayoutParams.MATCH_PARENT)

        window?.setWindowAnimations(R.style.DialogAnimation)

        return inflater.inflate(R.layout.article_webview_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        webview_back_button.setOnClickListener{
            this.dismiss()
        }

        loadArguments(arguments)

        article.observe(this, Observer {
            val title = it?.title
            val description = it?.description

            news_title.text = title

            when (it.simpleReading) {
                // Exibir informações com conteúdo carregado a partir do Regional News
                true -> {
                    webViewTitle.text = title
                    webViewDescription.text = description
                }

                // Exibir o Webview com conteúdo carregado do Website do autor da publicação
                false -> {
                    simpleReaderWv.visibility = View.GONE
                    mainWebview.settings.builtInZoomControls = true
                    mainWebview.settings.displayZoomControls = false
                    mainWebview.loadUrl(it.url)
                }
            }

            Toast.makeText(requireContext(), "$title - $description", Toast.LENGTH_SHORT).show()
        })
    }

    override fun onDismiss(dialog: DialogInterface) {
        findNavController().popBackStack()
        super.onDismiss(dialog)
    }

    // Métodos abaixo devem fazer parte do ViewModel
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

    val article: MutableLiveData<Article> = MutableLiveData()

    fun loadArguments(arguments: Bundle?) {
        if (arguments == null) {
            return
        }

        val article: Article? = arguments.get(ARTICLE_ARGUMENT) as Article
        this.article.postValue(article)
    }
}