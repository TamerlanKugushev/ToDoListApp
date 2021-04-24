package com.example.todolistapp.presentation.login

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.todolistapp.R
import com.example.todolistapp.data.repositories.Repository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment: Fragment() {

    companion object {
        fun newInstance(): LoginFragment {
            return LoginFragment()
        }
    }

    private var compositeDisposable = CompositeDisposable()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        registerButton.setOnClickListener {
            register()
        }
    }

    private fun register() {

        val email = emailEditText.text.toString()
        val password = passwordEditText.text.toString()

        Repository.registerUser(email, password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {

                },
                onError = {
                    Log.e("", "")
                }
            )
            .addTo(compositeDisposable)
    }

    override fun onStop() {
        super.onStop()

        compositeDisposable.clear()
    }
}