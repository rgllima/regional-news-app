package com.br.regionalnews.destinations

import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavDestination
import androidx.navigation.NavOptions
import androidx.navigation.Navigator
import com.br.regionalnews.R
import com.br.regionalnews.view.articlewebview.ArticleWebviewFragment

@Navigator.Name("article_webview")
class ArticleWebviewNavigator(
    private val manager: FragmentManager
) : Navigator<ArticleWebviewNavigator.Destination>() {
    override fun navigate(
        destination: Destination,
        args: Bundle?,
        navOptions: NavOptions?,
        navigatorExtras: Extras?
    ): NavDestination? {

        val articleWebviewFragment = ArticleWebviewFragment.newInstance()
        articleWebviewFragment.arguments = args
        articleWebviewFragment.show(manager, "webView")

        return Destination(this)
    }

    override fun createDestination(): Destination {
        return Destination(this)
    }

    override fun popBackStack(): Boolean {
        return true
    }

    class Destination(articleNavigator: ArticleWebviewNavigator) :
        NavDestination(articleNavigator)
}