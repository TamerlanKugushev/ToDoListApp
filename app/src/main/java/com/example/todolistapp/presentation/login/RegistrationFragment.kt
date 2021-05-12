package com.example.todolistapp.presentation.login

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.todolistapp.R
import com.example.todolistapp.Router
import com.example.todolistapp.domain.AuthorizationInteractor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_login.*

private var EMAIL_KEY: String? = "email"
private var PASSWORD_KEY: String? = "password"

class RegistrationFragment : Fragment() {

    companion object {

        private var EMAIL_KEY: String? = "email"
        private var PASSWORD_KEY: String? = "password"
        fun newInstance(email: String, password: String) =
            RegistrationFragment().apply {
                arguments = Bundle().apply {
                    putString(EMAIL_KEY, email)
                    putString(PASSWORD_KEY, password)
                }
            }
    }

    private val authorizationInteractor = AuthorizationInteractor()

    private var email: String? = null
    private var password: String? = null
    private var router: Router? = null
    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            email = it.getString(EMAIL_KEY)
            password = it.getString(PASSWORD_KEY)
            Log.i("TAG", email.toString())
            Log.i("TAG", password.toString())
        }
    }


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
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        continueButton.setOnClickListener {
            register()
        }
    }

    override fun onStop() {
        super.onStop()
        compositeDisposable.clear()
    }

    override fun onDetach() {
        super.onDetach()
        router = null
    }

    private fun register() {
        val name = nameEditText.text.toString()
        val age = ageEditText.text.toString()

        authorizationInteractor.registerUser(name, password, email, age)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {
                    router?.navigateToTasksScreen()
                    Log.i("REGISTRATION", it.toString())
                },
                onError = {
                    Log.e("REGISTRATION", it.toString())
                }
            ).addTo(compositeDisposable)
    }

}