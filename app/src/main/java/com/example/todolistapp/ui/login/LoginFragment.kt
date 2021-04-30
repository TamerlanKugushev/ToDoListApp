package com.example.todolistapp.ui.login

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.todolistapp.R
import com.example.todolistapp.Router
import com.example.todolistapp.data.repositories.Repository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_login.*

private var EMAIL_KEY: String? = "email"
private var PASSWORD_KEY: String? = "password"

class LoginFragment : Fragment() {
    private var email: String? = null
    private var password: String? = null

    companion object {
        @JvmStatic
        fun newInstance(email: String,password: String) =
            LoginFragment().apply {
                arguments = Bundle().apply {
                    putString(EMAIL_KEY, email)
                    putString(PASSWORD_KEY, password)
                }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            email = it.getString(EMAIL_KEY)
            password = it.getString(PASSWORD_KEY)
            Log.i("TAG", email.toString())
            Log.i("TAG", password.toString())
        }
    }

    private var router: Router? = null
    private var compositeDisposable = CompositeDisposable()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is Router) {
            router = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        router = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        continueButton.setOnClickListener {
            register()
        }
    }

    private fun register() {
        val name = nameEditText.text.toString().trim()
        val age = ageEditText.text.toString().trim()

        Repository
            .registerUser(name, password ?: "", email ?: "", age.toInt())
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

    override fun onStop() {
        super.onStop()
        compositeDisposable.clear()
    }

}