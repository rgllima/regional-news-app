package com.br.regionalnews.view.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.br.regionalnews.R
import kotlinx.android.synthetic.main.fragment_settings.*
import kotlinx.android.synthetic.main.fragment_settings.back_button2

class SettingsFragment : Fragment() {

    val cidades = arrayOf("Fortaleza", "Fortin", "Cascavel", "Quixadá", "Pacajus", "Floriano")

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initAdapters()

        back_button2.setOnClickListener{
            findNavController().popBackStack()
        }

        changeUserTypeBtn.setOnClickListener {
//            findNavController().popBackStack()
            findNavController().navigate(R.id.actionGoAuthorsListFragment)
        }
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
