package com.example.todolistapp

import android.content.Context
import android.content.SharedPreferences

class AppPreferencesHelper : PreferencesHelper {
    private val PREF_KEY_ACCESS_TOKEN = "PREF_KEY_ACCESS_TOKEN"

    private val PREF_KEY_USER_LOGIN_STATUS = "PREF_KEY_USER_LOGIN_STATUS"

    private var mPrefs: SharedPreferences? = null

    constructor(context: Context, prefFileName: String?) {
        mPrefs = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE)
    }

companion object{

}

    override fun getToken(): String? {
        return mPrefs?.getString(PREF_KEY_ACCESS_TOKEN, null);

    }

    override fun setToken(accessToken: String?) {
        mPrefs?.edit()?.putString(PREF_KEY_ACCESS_TOKEN, accessToken)?.apply();
    }


}