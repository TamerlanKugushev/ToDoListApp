package com.example.todolistapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.todolistapp.data.repositories.AuthorizationRepository
import com.example.todolistapp.presentation.registration.RegistrationFragment
import com.example.todolistapp.presentation.sign_in.SignInFragment
import com.example.todolistapp.presentation.tasks.TasksFragment
import com.example.todolistapp.presentation.welcome.WelcomeFragment
import io.reactivex.disposables.CompositeDisposable

class MainActivity : AppCompatActivity(), Router {


    @SuppressLint("CommitPrefEdits")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            val initFragment: Fragment
            if (AuthorizationRepository.isUserAuthorized()) {
                initFragment = TasksFragment.newInstance()
            } else {
                initFragment = WelcomeFragment.newInstance()
            }

            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragmentContainer, initFragment)
                .commit()
        }

    }


    override fun navigateToLoginScreen() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainer, SignInFragment.newInstance())
            .addToBackStack(null)
            .commit()
    }


    override fun navigateToRegistrationScreen() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainer, RegistrationFragment.newInstance())
            .addToBackStack(null)
            .commit()
    }


    override fun navigateToTasksScreen() {
        supportFragmentManager.popBackStack()

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainer, TasksFragment.newInstance())
            .commit()
    }

    override fun openRootSignInScreen() {
        supportFragmentManager.popBackStack()

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainer, SignInFragment.newInstance())
            .addToBackStack(null)
            .commit()
    }
}

interface Router {
    fun navigateToLoginScreen()
    fun navigateToRegistrationScreen()
    fun navigateToTasksScreen()
    fun openRootSignInScreen()
}