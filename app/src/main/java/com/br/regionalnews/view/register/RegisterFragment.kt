package com.br.regionalnews.view.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.br.regionalnews.R
import kotlinx.android.synthetic.main.fragment_register.*

class RegisterFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initButtons()
    }

    private fun initButtons() {
        loginNowBtn.setOnClickListener{
            findNavController().popBackStack()
        }

        registerBtn.setOnClickListener{
            findNavController().navigate(R.id.actionGoHome2)
        }
    }
}