package com.gumibom.travelmaker.domain.login

import com.gumibom.travelmaker.data.repository.login.LoginRepository
import com.gumibom.travelmaker.data.repository.login.LoginRepositoryImpl
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val loginRepositoryImpl: LoginRepository
){
    
}