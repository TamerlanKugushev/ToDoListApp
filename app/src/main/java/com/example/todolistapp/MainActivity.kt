package com.example.todolistapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.todolistapp.data.repositories.Repository
import com.example.todolistapp.presentation.login.RegistrationFragment
import com.example.todolistapp.presentation.sign_in.SignInFragment
import com.example.todolistapp.presentation.tasks.TasksFragment
import com.example.todolistapp.presentation.welcome.WelcomeFragment

class MainActivity : AppCompatActivity(), Router {


    @SuppressLint("CommitPrefEdits")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {

            val initFragment = if (Repository.isUserAuthorized()) {
                TasksFragment.newInstance()
            } else {
                WelcomeFragment.newInstance()
            }

            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragmentContainer, initFragment)
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