package com.example.todolistapp

import com.example.todolistapp.presentation.add_task.AddTaskDialog
import com.example.todolistapp.presentation.registration.RegistrationFragment
import com.example.todolistapp.presentation.sign_in.SignInFragment
import com.example.todolistapp.presentation.tasks.TasksFragment
import com.example.todolistapp.presentation.welcome.WelcomeFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

object Screens {

    fun WelcomeScreen() = FragmentScreen { WelcomeFragment.newInstance() }

    fun RegistrationScreen() = FragmentScreen { RegistrationFragment.newInstance() }

    fun SignInScreen() = FragmentScreen { SignInFragment.newInstance() }

    fun TasksScreen() = FragmentScreen { TasksFragment.newInstance() }
}