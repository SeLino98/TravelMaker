package com.gumibom.travelmaker.util

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

private const val TAG = "AccessTokenInterceptor_싸피"
class AccessTokenInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var token = ApplicationClass.sharedPreferencesUtil.getToken()

        val request = chain.request()
            .newBuilder()
            .addHeader("Authorization", token)
            .build()

        val response = chain.proceed(request)

        /**
         * 만약 accessToken이 만료된다면
         */
        if (response.code == 401) {

        }
        return response
    }

    private fun getNewAccessToken() : String {
        return ""
    }
}