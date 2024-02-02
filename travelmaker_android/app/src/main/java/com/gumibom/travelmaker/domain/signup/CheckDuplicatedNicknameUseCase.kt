package com.gumibom.travelmaker.domain.signup

import com.gumibom.travelmaker.data.repository.signup.SignupRepository
import javax.inject.Inject

class CheckDuplicatedNicknameUseCase @Inject constructor(
    private val repository:SignupRepository
){
    suspend fun checkDuplicatedNick(nickname:String):Boolean?{
        val response = repository.checkDuplicateNickname(nickname)
        return if (response.isSuccessful){
            response.body()
        }else{
            null
        }
    }



}