package com.example.todolistapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.todolistapp.data.repositories.AuthorizationRepository
import com.example.todolistapp.presentation.sign_in.SignInFragment
import com.example.todolistapp.presentation.tasks.TasksFragment
import com.example.todolistapp.presentation.welcome.WelcomeFragment
import com.github.terrakok.cicerone.androidx.AppNavigator

class MainActivity : AppCompatActivity(), Router {

    private val navigator = AppNavigator(this, R.id.fragmentContainer)
    private val navigatorHolder = BaseApplication.instance.navigatorHolder

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

    override fun onResumeFragments() {
        super.onResumeFragments()

        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()

        super.onPause()
    }

    override fun navigateToLoginScreen() {
        supportFragmentManager.popBackStack()

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainer, SignInFragment.newInstance())
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
        supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)

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