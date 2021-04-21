package com.example.todolistapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.todolistapp.data.models.UserRequest
import com.example.todolistapp.data.models.UserResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val userService = RetrofitHolder.userService
    private var compositeDisposable = CompositeDisposable()
    private var response: String = ""
    private var token = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        buttonLogin.setOnClickListener(this)
        buttonLogout.setOnClickListener(this)
        buttonClear.setOnClickListener(this)
        buttonGetLogged.setOnClickListener(this)
        buttonDeleteUser.setOnClickListener(this)
        buttonUpdateUserProfile.setOnClickListener(this)


    }

    private fun login() {
        val body = UserRequest(
            email = "tamirlankugushev@mail.ru",
            password = "wsde1320"
        )

        buttonLogin.setOnClickListener {
            userService.loginUser(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onSuccess = {
                        Log.d("mLog", it.toString())
                        token = it.token
                        setResponse(it)
                    },
                    onError = {
                        Log.d("mLog", it.toString())
                    }
                ).addTo(compositeDisposable)
        }
    }


    private fun logout() {
        buttonLogin.setOnClickListener {
            userService.logoutUser(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onSuccess = {
                        Log.d("mLog", it.toString())
                        textView.text = it.toString()
                    },
                    onError = {
                        Log.d("mLog", it.toString())
                    }
                ).addTo(compositeDisposable)
        }
    }


    private fun getLoggedInUserViaToken() {
        buttonLogin.setOnClickListener {
            userService.getLoggedInUserViaToken(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onSuccess = {
                        Log.d("mLog", it.toString())
                        textView.text = it.toString()
                    },
                    onError = {
                        Log.d("mLog", it.toString())
                    }
                ).addTo(compositeDisposable)
        }
    }

    private fun clearTextView() {
        textView.text = ""
    }


    private fun setResponse(userResponse: UserResponse) {
        textView.text = userResponse.toString()
    }


    override fun onPause() {
        super.onPause()
        compositeDisposable.clear()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.buttonLogin -> {
                login()
            }

            R.id.buttonClear -> {
                clearTextView()
            }
            R.id.buttonLogout ->{
                logout()
            }
            R.id.buttonGetLogged ->{
                getLoggedInUserViaToken()
            }
        }
    }
}