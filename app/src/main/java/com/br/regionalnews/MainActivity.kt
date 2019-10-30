package com.br.regionalnews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.GravityCompat
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.br.regionalnews.destinations.ArticleWebviewNavigator
import com.br.regionalnews.destinations.SearchArticleNavigator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val searchArticleNavigator = SearchArticleNavigator(supportFragmentManager)
        navHostFragment.findNavController().navigatorProvider.addNavigator(searchArticleNavigator)

        // Rever isso aqui
        val articleWebviewNavigator = ArticleWebviewNavigator(supportFragmentManager)
        navHostFragment.findNavController().navigatorProvider.addNavigator(articleWebviewNavigator)

        val inflater = navHostFragment.findNavController().navInflater
        val graph = inflater.inflate(R.navigation.navigation)
        navHostFragment.findNavController().graph = graph

        findNavController(this, R.id.navHostFragment)
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onSupportNavigateUp() = findNavController(this, R.id.navHostFragment).navigateUp()
}
