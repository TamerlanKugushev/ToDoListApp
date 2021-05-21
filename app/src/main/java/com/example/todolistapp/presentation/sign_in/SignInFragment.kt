package com.example.todolistapp.presentation.sign_in

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.todolistapp.R
import com.example.todolistapp.Router
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

    private var router: Router? = null
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is Router) {
            router = context
        }
    }

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
        register_btn.setOnClickListener {
            onRegisterButtonClicked()
        }
        //Переход на экран задач
        signIn_btn.setOnClickListener {
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

    override fun onDetach() {
        super.onDetach()
        router = null
    }

    override fun navigateToRegistrationScreen() {
        router?.navigateToRegistrationScreen()
    }

    override fun navigateToTasksScreen() {
        router?.navigateToTasksScreen()
    }

    override fun showProgressBar() {
        signinProgressBar.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        signinProgressBar.visibility=View.INVISIBLE
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