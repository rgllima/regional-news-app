package com.br.regionalnews.view.initial

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.br.regionalnews.view.article.ArticlesListActivity
import com.br.regionalnews.view.author.AuthorsListActivity

class InitialPageAdapter(fm: FragmentManager): FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                ArticlesListActivity()
            }
            1 -> AuthorsListActivity()
            else -> {
                return FirstFragment()
            }
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> "Artigos"
            1 -> "Favoritos"
            else -> {
                return "Artigos"
            }
        }
    }
}