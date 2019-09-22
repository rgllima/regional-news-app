package com.br.regionalnews.view.author

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.br.regionalnews.R
import com.br.regionalnews.model.Author
import kotlinx.android.synthetic.main.authors_list_item.view.*

class AuthorAdapter : BaseAdapter {
    var authorsList = ArrayList<Author>()
    var context: Context? = null

    constructor(context: Context?, authorsList: ArrayList<Author>) : super() {
        this.context = context
        this.authorsList = authorsList
    }

    override fun getCount(): Int {
        return authorsList.size
    }

    override fun getItem(position: Int): Any {
        return authorsList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val author = this.authorsList[position]

        var inflator = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var authorView = inflator.inflate(R.layout.authors_list_item, null)

        authorView.authorName.text = author.name
        authorView.authorURL.text = author.url

        return authorView
    }
}
