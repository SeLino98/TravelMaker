package com.gumibom.travelmaker.domain.signup

import com.gumibom.travelmaker.data.dto.request.UserRequestDTO
import com.gumibom.travelmaker.data.repository.signup.SignupRepository
import javax.inject.Inject

class SaveUserInfoUseCase @Inject constructor(
    private val repository: SignupRepository //레포에 있는 값을 받는다
) {
    //코루틴 통신
    suspend fun saveUserInfo(userdata:UserRequestDTO):Boolean?{
        val response = repository.saveUserData(userdata)
        return if (response.isSuccessful){
            response.body()
        }else{
            null
        }
    }
}