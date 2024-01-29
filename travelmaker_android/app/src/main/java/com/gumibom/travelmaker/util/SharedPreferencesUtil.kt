package com.gumibom.travelmaker.util

import android.content.Context
import android.content.SharedPreferences
import com.gumibom.travelmaker.model.User

private const val TAG = "SharedPreferencesUtil_싸피"
class SharedPreferencesUtil(context : Context) {

    val SHARED_PREFERENCES_NAME = "travelMaker_preference"

    var preferences: SharedPreferences =
        context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)

    // sharedpreference에 로그인 정보 저장하기
    fun addUser(user : User){
        val editor = preferences.edit()
        editor.putString("loginId", user.loginId)
        editor.putString("password", user.password)
        editor.apply()
    }

    fun getUser(): User {
        val loginId = preferences.getString("loginId", "")

        return if (loginId != ""){
            val password = preferences.getString("password", "")
            User(loginId!!, password!!)
        }else{
            User()
        }
    }

    fun deleteUser(){
        //preference 지우기
        val editor = preferences.edit()
        editor.clear()
        editor.apply()
    }
}