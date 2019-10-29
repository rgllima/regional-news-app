package com.br.regionalnews.destinations

import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavDestination
import androidx.navigation.NavOptions
import androidx.navigation.Navigator
import com.br.regionalnews.view.searcharticle.SearchArticleDialog

@Navigator.Name("search_article")
class SearchArticleNavigator(
    private val manager: FragmentManager
) : Navigator<SearchArticleNavigator.Destination>() {

    override fun navigate(
        destination: Destination,
        args: Bundle?,
        navOptions: NavOptions?,
        navigatorExtras: Extras?
    ): NavDestination? {
        val dialog = SearchArticleDialog()
        dialog.show(manager, SearchArticleDialog.TAG)
        return destination
    }

    override fun createDestination(): Destination {
        return Destination(this)
    }

    override fun popBackStack(): Boolean {
        return true
    }

    class Destination(searchArticleNavigator: SearchArticleNavigator) :
        NavDestination(searchArticleNavigator)
}