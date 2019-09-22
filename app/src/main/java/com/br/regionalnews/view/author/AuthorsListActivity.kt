package com.br.regionalnews.view.author

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.br.regionalnews.R
import com.br.regionalnews.model.Author
import kotlinx.android.synthetic.main.activity_authors_list.*

class AuthorsListActivity : Fragment() {

    var adapter: AuthorAdapter? = null
    var authorsList = ArrayList<Author>();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.activity_authors_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        authorsList.add(Author("Victor", "#", false))
        authorsList.add(Author("Victor", "#", false))
        authorsList.add(Author("Victor", "#", false))
        authorsList.add(Author("Victor", "#", false))
        authorsList.add(Author("Victor", "#", false))
        authorsList.add(Author("Victor", "#", false))
        authorsList.add(Author("Victor", "#", false))
        authorsList.add(Author("Victor", "#", false))
        authorsList.add(Author("Victor", "#", false))

        adapter = AuthorAdapter(this.context, authorsList)

        gridViewAuthors.adapter = adapter
    }


}
