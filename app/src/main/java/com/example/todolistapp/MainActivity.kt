package com.example.todolistapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.todolistapp.ui.login.RegistrationFragment
import com.example.todolistapp.ui.registration.SignInFragment
import com.example.todolistapp.ui.tasks.TasksFragment
import com.example.todolistapp.ui.welcome.WelcomeFragment
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
            val welcomeFragment = WelcomeFragment.newInstance()
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragmentContainer, welcomeFragment)
                .commit()
        }


    }


    override fun navigateToSignInScreen() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainer, SignInFragment.newInstance())
            .addToBackStack(null)
            .commit()
    }


    override fun navigateToRegistrationScreen(email: String, password: String) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainer, RegistrationFragment.newInstance(email, password))
            .addToBackStack(null)
            .commit()
    }


    override fun navigateToTasksScreen() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainer, TasksFragment.newInstance())
            .addToBackStack(null)
            .commit()
    }

}

interface Router {
    fun navigateToSignInScreen()
    fun navigateToRegistrationScreen(email: String, password: String)
    fun navigateToTasksScreen()
}