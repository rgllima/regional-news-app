package com.br.regionalnews.view.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.fragment.app.Fragment
import com.br.regionalnews.R
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment: Fragment() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initButtons()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    private fun initButtons() {
        registerNowBtn.setOnClickListener{
            findNavController().navigate(R.id.actionGoRegister)
        }

        loginBtn.setOnClickListener{
            findNavController().navigate(R.id.actionGoHome1)
        }
    }
}