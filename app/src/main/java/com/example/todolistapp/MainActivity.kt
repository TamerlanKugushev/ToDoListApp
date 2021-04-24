package com.example.todolistapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.todolistapp.presentation.login.LoginFragment
import com.example.todolistapp.presentation.welcome.WelcomeFragment
import io.reactivex.disposables.CompositeDisposable

class MainActivity : AppCompatActivity(), Router {

    private val unauthorizedUserService = RetrofitHolder.unauthorizedUserService
    private val authorizedUserService = RetrofitHolder.authorizedUserService
    private var compositeDisposable = CompositeDisposable()
    private var response: String = ""

    @SuppressLint("CommitPrefEdits")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            val welcomeFragment = WelcomeFragment.newInstance()
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, welcomeFragment)
                .commit()
        }

        //prefsHelp = AppPreferencesHelper(BaseApplication.instance.baseContext)


        /*buttonLogin.setOnClickListener { login() }
        buttonLogout.setOnClickListener { logout() }
        buttonGetLogged.setOnClickListener { getLoggedInUserViaToken() }
        buttonDeleteUser.setOnClickListener { deleteUser() }
        buttonClear.setOnClickListener { clearTextView() }
        // buttonUpdateUserProfile.setOnClickListener { updateUser() }
        //buttonRegisterUser.setOnClickListener { registerUser() }*/
    }

    override fun navigateToLoginScreen() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, LoginFragment.newInstance())
            .addToBackStack(null)
            .commit()
    }

    /*private fun registerUser() {
        val body = UserRegisterRequest(
            name = "Tamirlan Kugushev",
            age = 20,
            email = "tamirlankugushev@mail.ru",
            password = "wsde1320"
        )
        userService.registerUser(body)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {
                    Log.d("mLog", it.toString())
                    token = it.token
                    textView.text=it.toString()
                },
                onError = {
                    Log.d("mLog", it.toString())
                }
            ).addTo(compositeDisposable)
    }

    private fun login() {
        val body = UserRequest(
            email = "tamirlankugushev@mail.ru",
            password = "wsde1320"
        )
        unauthorizedUserService.loginUser(body)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {
                    Log.d("mLog", it.toString())
                    token = it.token
                    textView.text = it.toString()
                },
                onError = {
                    Log.d("mLog", it.toString())
                }
            ).addTo(compositeDisposable)
        prefsHelp?.setToken(token)
    }

    private fun updateUser() {
        val body = UserUpdateRequest(25)
        authorizedUserService.updateUser("Bearer $token", body)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {
                    Log.d("mLog", it.toString())
                    textView.text = it.userUpdate.age.toString()
                },
                onError = {
                    Log.d("mLog", it.toString())
                }
            ).addTo(compositeDisposable)
    }

    private fun logout() {
        authorizedUserService.logoutUser("Bearer $token")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {
                    Log.d("mLog", it.toString())
                    textView.text = it.success.toString()
                },
                onError = {
                    Log.d("mLog", it.toString())
                }
            ).addTo(compositeDisposable)
    }

    private fun getLoggedInUserViaToken() {
        authorizedUserService.getLoggedInUserViaToken("Bearer $token")
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

    private fun deleteUser() {
        authorizedUserService.deleteUser("Bearer $token")
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

    override fun onPause() {
        super.onPause()
        compositeDisposable.clear()
    }*/
}

interface Router {

    fun navigateToLoginScreen()

}