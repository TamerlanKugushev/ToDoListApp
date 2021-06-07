package com.example.todolistapp.presentation.welcome

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.todolistapp.R
import com.example.todolistapp.utils.BaseFragment
import com.example.todolistapp.utils.BasePresenter
import com.example.todolistapp.utils.BaseView
import com.example.todolistapp.utils.PresentersStorage
import kotlinx.android.synthetic.main.fragment_welcome.*


class WelcomeFragment : BaseFragment() {

    companion object {
        fun newInstance(): WelcomeFragment {
            return WelcomeFragment()
        }
    }

    private lateinit var presenter: WelcomePresenter

    override fun attachPresenter() {
        val presenter = PresentersStorage.getPresenter(viewId)
        if (presenter !is WelcomePresenter) {
            this.presenter = WelcomePresenter()
            return
        }
        this.presenter = presenter
    }

    override fun getPresenter(): WelcomePresenter {
        return presenter
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_welcome, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        email_btn.setOnClickListener {
            presenter.navigateToSignInScreen()
        }
    }
}