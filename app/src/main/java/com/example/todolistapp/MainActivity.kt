package com.example.todolistapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.todolistapp.presentation.login.LoginFragment
import com.example.todolistapp.presentation.password.PasswordFragment
import com.example.todolistapp.presentation.registration.RegistrationFragment
import com.example.todolistapp.presentation.welcome.WelcomeFragment
import io.reactivex.disposables.CompositeDisposable

class MainActivity : AppCompatActivity(), Router {

    private val unauthorizedUserService = RetrofitHolder.unauthorizedUserService
    private val authorizedUserService = RetrofitHolder.authorizedUserService
    private var compositeDisposable = CompositeDisposable()


    @SuppressLint("CommitPrefEdits")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //prefsHelp = AppPreferencesHelper(this)

        if (savedInstanceState == null) {
            val welcomeFragment = WelcomeFragment()
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragmentContainer, welcomeFragment)
                .commit()
        }


    }


    override fun navigateToRegistrationScreen(email: String) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainer, RegistrationFragment.newInstance(email))
            .addToBackStack(null)
            .commit()
    }


    override fun navigateToLoginScreen() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainer, LoginFragment.newInstance())
            .addToBackStack(null)
            .commit()
    }

    override fun navigateToPasswordScreen() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainer, PasswordFragment.newInstance())
            .addToBackStack(null)
            .commit()
    }
}

interface Router {
    fun navigateToRegistrationScreen(email: String)
    fun navigateToLoginScreen()
    fun navigateToPasswordScreen()
}