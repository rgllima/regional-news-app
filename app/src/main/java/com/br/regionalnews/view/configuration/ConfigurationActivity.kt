package com.br.regionalnews.view.configuration

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import com.br.regionalnews.R
import kotlinx.android.synthetic.main.activity_configuration.*

class ConfigurationActivity : AppCompatActivity() {

    val cidades = arrayOf("Fortaleza", "Fortin", "Cascavel", "Quixadá", "Pacajus", "Floriano")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_configuration)


        var adapter =
            ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, cidades)
        autocompleteLocation.setAdapter(adapter)
        autocompleteLocation.setText(cidades[0])
    }
}
