package com.example.todolistapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.todolistapp.data.models.login.UserRequest
import com.example.todolistapp.data.models.login.UserResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val userService = RetrofitHolder.unathorizedUserService
    private var compositeDisposable = CompositeDisposable()
    private var response: String = ""
    private var token = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonLogin.setOnClickListener {
            login()
        }
        buttonLogout.setOnClickListener {
            logout()
        }
        buttonClear.setOnClickListener {
            clearTextView()
        }
        buttonGetLogged.setOnClickListener {
            getLoggedInUserViaToken()
        }
        buttonDeleteUser.setOnClickListener {
        }
        buttonUpdateUserProfile.setOnClickListener {
        }
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
        userService.logoutUser(token)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onComplete = {
                    textView.text = "Запрос зпрошел успешно"
                },
                onError = {
                    //Log.d("mLog", it.toString())
                    Log.e("mLog", it.toString())
                }
            ).addTo(compositeDisposable)
    }


    private fun getLoggedInUserViaToken() {
        userService.getLoggedInUserViaToken("Bearer $token")
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
}