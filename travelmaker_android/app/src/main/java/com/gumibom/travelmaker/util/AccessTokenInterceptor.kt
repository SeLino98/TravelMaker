package com.gumibom.travelmaker.util

import okhttp3.Interceptor
import okhttp3.Response

class AccessTokenInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var token = ApplicationClass.sharedPreferencesUtil.getToken()

        val request = chain.request()
            .newBuilder()
            .addHeader("Authorization", token)
            .build()

        val response = chain.proceed(request)

        return response
    }
}