package com.example.todolistapp.activities

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.todolistapp.R
import com.example.todolistapp.Screens
import com.example.todolistapp.data.repositories.AuthorizationRepository
import com.example.todolistapp.utils.BaseApplication
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator

class MainActivity : AppCompatActivity() {

    private val navigator = AppNavigator(this, R.id.fragmentContainer)
    private val navigatorHolder = BaseApplication.instance.navigatorHolder
    private val router: Router = BaseApplication.instance.router

    @SuppressLint("CommitPrefEdits")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            if (AuthorizationRepository.isUserAuthorized()) {
                router.newRootScreen(Screens.TasksScreen())
            } else {
                router.newRootScreen(Screens.WelcomeScreen())
            }
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

