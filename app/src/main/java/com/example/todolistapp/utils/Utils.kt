package com.example.todolistapp.utils

import android.util.Log
import com.google.firebase.crashlytics.FirebaseCrashlytics

fun logException(obj: Any, throwable: Throwable) {
    Log.w(obj.javaClass.simpleName, throwable)
    FirebaseCrashlytics.getInstance().recordException(throwable)
}