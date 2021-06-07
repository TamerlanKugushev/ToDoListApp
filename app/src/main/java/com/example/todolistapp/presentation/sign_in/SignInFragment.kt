package com.example.todolistapp.presentation.sign_in

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.todolistapp.R
import com.example.todolistapp.utils.BaseFragment
import com.example.todolistapp.utils.PresentersStorage
import kotlinx.android.synthetic.main.fragment_signin.*

class SignInFragment : BaseFragment(), SignInView {

    companion object {
        fun newInstance(): SignInFragment {
            return SignInFragment()
        }
    }

    private lateinit var presenter: SignInPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_signin, container, false)
    }

    override fun attachPresenter() {
        val presenter = PresentersStorage.getPresenter(viewId)
        if (presenter !is SignInPresenter) {
            this.presenter = SignInPresenter()
            return
        }
        this.presenter = presenter
    }

    override fun getPresenter(): SignInPresenter {
        return presenter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Переход на экран регистрации
        registerButton.setOnClickListener {
            onRegisterButtonClicked()
        }
        //Переход на экран задач
        signInButton.setOnClickListener {
            login()
        }
    }

    override fun onStart() {
        super.onStart()
        presenter.bindView(this)
    }

    override fun onStop() {
        super.onStop()
        presenter.unbindView()
    }

    override fun showProgressBar() {
        signinProgressBar.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        signinProgressBar.visibility = View.GONE
    }

    private fun onRegisterButtonClicked() {
        presenter.onRegisterButtonClicked()
    }

    private fun login() {
        presenter.login(
            email = editTextEmailRegistration.text.toString(),
            password = editTextPasswordRegistration.text.toString()
        )
    }

}