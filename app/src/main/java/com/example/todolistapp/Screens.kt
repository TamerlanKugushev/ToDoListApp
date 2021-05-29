package com.example.todolistapp

import com.example.todolistapp.presentation.registration.RegistrationFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

object Screens {
    fun RegistrationScreen() = FragmentScreen { RegistrationFragment.newInstance() }
}