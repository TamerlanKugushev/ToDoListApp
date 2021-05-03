package com.example.todolistapp.ui.registration

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
import kotlinx.android.synthetic.main.fragment_registration.*

class SignInFragment : Fragment() {

    companion object {
        fun newInstance(): SignInFragment {
            return SignInFragment()
        }
    }

    private var router: Router? = null
    private var compositeDisposable = CompositeDisposable()

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
        return inflater.inflate(R.layout.fragment_registration, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Переход на экран регистрации
        registerButton.setOnClickListener {
            navigateToRegScreen()
        }
        //Переход на экран задач
        signInButton.setOnClickListener {
            login()
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

    private fun navigateToRegScreen() {
        val email = editTextEmailRegistration.text.toString().trim()
        val password = editTextPasswordRegistration.text.toString().trim()
        router?.navigateToRegistrationScreen(email, password)
    }

    private fun login() {
        val email = editTextEmailRegistration.text.toString().trim()
        val password = editTextPasswordRegistration.text.toString().trim()

        Repository
            .loginUser(email, password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {
                    router?.navigateToTasksScreen()
                    Log.i("LOGIN", it.toString())
                },
                onError = {
                    Log.e("LOGIN", it.toString())
                }
            ).addTo(compositeDisposable)
    }

}