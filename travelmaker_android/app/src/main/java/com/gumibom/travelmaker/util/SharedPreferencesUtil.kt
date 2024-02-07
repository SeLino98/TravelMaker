package com.gumibom.travelmaker.util

import android.content.Context
import android.content.SharedPreferences
import com.gumibom.travelmaker.model.User
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

private const val TAG = "SharedPreferencesUtil_싸피"
class SharedPreferencesUtil (context : Context) {
    val SHARED_PREFERENCES_NAME = "travelMaker_preference"
    var preferences: SharedPreferences =
        context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
    // sharedpreference에 로그인 정보 저장하기
    fun addToken(accessToken : String){
        val editor = preferences.edit()
        editor.putString("accessToken", accessToken)
        editor.apply()
    }
    fun getToken(): String {
        val accessToken = preferences.getString("accessToken", "")

        return if (accessToken != ""){
            accessToken!!
        }else{
            ""
        }
    }
    fun deleteToken(){
        //preference 지우기
        val editor = preferences.edit()
        editor.remove("accessToken")
        editor.apply()
    }
    fun addGoogleEmail(email : String) {
        val editor = preferences.edit()
        editor.putString("googleEmail", email)
        editor.apply()
    }
    fun getGoogleEmail() : Boolean {
        val isEmail = preferences.getString("googleEmail", "")
        return isEmail != ""
    }
    fun deleteGoogleEmail() {
        //preference 지우기
        val editor = preferences.edit()
        editor.clear()
        editor.apply()
    }
}