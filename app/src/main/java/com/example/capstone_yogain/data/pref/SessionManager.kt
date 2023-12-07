package com.example.capstone_yogain.data.pref

import android.content.Context
import android.content.SharedPreferences

class SessionManager (context: Context){

    private var prefs : SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    private val edit = prefs.edit()

    fun login(key: String, value: String){
        edit.putString(key, value)
        edit.apply()
    }

    fun setSession(key: String, value: Boolean){
        edit.putBoolean(key, value)
        edit.apply()
    }

    fun checkSession(key: String) : Boolean {
        return prefs.getBoolean(key, false)
    }

    fun getToken(key: String) : String? {
        return prefs.getString(key, null)
    }

    fun logOut() {
        edit.clear()
        edit.apply()
    }

    val isLogin = prefs.getBoolean(KEY_LOGIN,  false)
    val getEmail = prefs.getString(KEY_USERNAME, "")
    val getUsername = prefs.getString(KEY_USERNAME, "")

    companion object{
        const val KEY_LOGIN = "key_login"
        const val PREFS_NAME = "prefs_name"
        const val KEY_TOKEN = "key_token"
        const val KEY_USER_ID = "key_user_id"
        const val KEY_USERNAME = "key_username"
        const val KEY_EMAIL = "key_email"
    }
}