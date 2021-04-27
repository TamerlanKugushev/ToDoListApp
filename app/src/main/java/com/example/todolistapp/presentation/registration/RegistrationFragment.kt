package com.example.todolistapp.presentation.registration

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.todolistapp.R
import com.example.todolistapp.data.models.registration.UserRegisterRequest
import com.example.todolistapp.data.repositories.Repository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_registration.*

private var EMAIL_KEY: String? = "email"

class RegistrationFragment : Fragment() {
    private var email: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            email = it.getString(EMAIL_KEY)
            Log.d("email", email.toString())
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(email: String) =
            RegistrationFragment().apply {
                arguments = Bundle().apply {
                    putString(EMAIL_KEY, email)
                }
            }
    }

    private var compositeDisposable = CompositeDisposable()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_registration, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        textView7.text = "Регистрация с использованием $email"
        buttonRegistration.setOnClickListener { register() }
    }

    private fun register() {
        val name = editTextNameRegistration.text.toString()
        val password = editTextPasswordRegistration.text.toString()

        Repository
            .registerUser(name, password, email)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {
                    Log.i("TAG", it.toString())
                },
                onError = {
                    Log.e("TAG", it.toString())
                }
            ).addTo(compositeDisposable)
    }

    override fun onStop() {
        super.onStop()
        compositeDisposable.clear()
    }

}