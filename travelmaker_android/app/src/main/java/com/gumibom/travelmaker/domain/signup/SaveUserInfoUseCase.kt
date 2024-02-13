package com.gumibom.travelmaker.domain.signup

import com.google.gson.Gson
import com.gumibom.travelmaker.data.dto.request.RecordRequestDTO
import com.gumibom.travelmaker.data.dto.request.SignInUserDataRequestDTO
import com.gumibom.travelmaker.data.dto.response.IsSuccessResponseDTO
import com.gumibom.travelmaker.data.repository.signup.SignupRepository
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File
import javax.inject.Inject

class SaveUserInfoUseCase @Inject constructor(
    private val repository: SignupRepository //레포에 있는 값을 받는다
) {
    //코루틴 통신
    suspend fun saveUserInfo(userdata: SignInUserDataRequestDTO): IsSuccessResponseDTO?{
        val requestBody = createRequestBody(userdata)
        var multiImage : MultipartBody.Part? = null
        if (image.isNotEmpty()) {
            multiImage = convertImageMultiPart(image)
        }
        val response = repository.saveUserData(userdata)
        return if (response.isSuccessful){
            response.body()
        }else{
            null
        }
    }
    private fun createRequestBody(recordRequestDTO: SignInUserDataRequestDTO): RequestBody {
        val gson = Gson()
        val productJson = gson.toJson(recordRequestDTO)
        return productJson.toRequestBody("application/json".toMediaTypeOrNull())
    }

    private fun convertImageMultiPart(image : String): MultipartBody.Part {
        val file = File(image)
        val requestFile = file.asRequestBody("image/*".toMediaTypeOrNull())

        return MultipartBody.Part.createFormData("image", file.name, requestFile)
    }
}