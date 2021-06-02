package com.example.todolistapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.todolistapp.data.repositories.AuthorizationRepository
import com.example.todolistapp.presentation.tasks.TasksFragment
import com.example.todolistapp.presentation.welcome.WelcomeFragment
import com.github.terrakok.cicerone.androidx.AppNavigator

class MainActivity : AppCompatActivity() {

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

}

