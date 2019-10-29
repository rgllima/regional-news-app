package com.br.regionalnews.view.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.br.regionalnews.R
import kotlinx.android.synthetic.main.fragment_settings.*

class SettingsFragment : Fragment() {

    val cidades = arrayOf("Fortaleza", "Fortin", "Cascavel", "Quixadá", "Pacajus", "Floriano")

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initAdapters()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    private fun initAdapters(){
        val adapter =
            ArrayAdapter<String>(requireContext(), android.R.layout.simple_dropdown_item_1line, cidades)
        autocompleteLocation.setAdapter(adapter)
        autocompleteLocation.setText(cidades[0])
    }
}
