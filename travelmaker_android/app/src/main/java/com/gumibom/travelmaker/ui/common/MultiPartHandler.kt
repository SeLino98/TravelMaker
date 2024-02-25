package com.gumibom.travelmaker.ui.common

import com.google.gson.Gson
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File

class MultiPartHandler {

    fun convertMultiPart(filePath : String, imageKey : String) : MultipartBody.Part {
        val file = File(filePath)
        val requestFile = file.asRequestBody("image/*".toMediaTypeOrNull())

        return MultipartBody.Part.createFormData(imageKey, file.name, requestFile)
    }

    fun <T> createRequestBody(requestDTO : T) : RequestBody {
        val gson = Gson()
        val productJson = gson.toJson(requestDTO)
        return productJson.toRequestBody("application/json".toMediaTypeOrNull())
    }
}