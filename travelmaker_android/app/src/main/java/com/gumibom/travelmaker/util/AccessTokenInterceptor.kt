package com.gumibom.travelmaker.util

import com.gumibom.travelmaker.data.api.token.TokenService
import com.gumibom.travelmaker.data.repository.login.LoginRepository
import com.gumibom.travelmaker.model.JwtToken
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

private const val TAG = "AccessTokenInterceptor_싸피"
class AccessTokenInterceptor @Inject constructor(
    private val tokenService : TokenService
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = ApplicationClass.sharedPreferencesUtil.getToken()

        val request = chain.request()
            .newBuilder()
            .addHeader("Authorization", token)
            .build()

        val response = chain.proceed(request)

        /**
         * 만약 accessToken이 만료된다면 newResponse를 반환
         */
//        if (response.code == 401) {
//            val newJwtToken = runBlocking {
//                val refreshToken = ApplicationClass.sharedPreferencesUtil.getRefreshToken()
//                val loginResponseDTO = loginRepositoryImpl.getNewAccessToken(refreshToken)
//
//                JwtToken(loginResponseDTO.accessToken, loginResponseDTO.refreshToken)
//            }
//
//            if (newJwtToken.accessToken.isNotEmpty()) {
//                ApplicationClass.sharedPreferencesUtil.addToken(newJwtToken.accessToken)
//                ApplicationClass.sharedPreferencesUtil.addRefreshToken(newJwtToken.refreshToken)
//
//                val newToken = ApplicationClass.sharedPreferencesUtil.getToken()
//
//                val newRequest = chain.request()
//                    .newBuilder()
//                    .addHeader("Authorization", newToken)
//                    .build()
//
//                return chain.proceed(newRequest)
//            }
//        }
        /**
         * 아니면 그냥 Response를 반환
         */
        return response
    }


}