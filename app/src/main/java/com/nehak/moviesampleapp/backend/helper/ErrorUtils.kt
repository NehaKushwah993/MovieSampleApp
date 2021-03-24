package com.nehak.moviesampleapp.backend.helper

import com.nehak.moviesampleapp.backend.models.ErrorApi
import retrofit2.Response
import retrofit2.Retrofit
import java.io.IOException

/**
 * Created by Neha Kushwah on 3/24/21.
 */
class ErrorUtils {

    companion object {
        fun parseError(response: Response<*>, retrofit: Retrofit): ErrorApi? {
            val converter =
                retrofit.responseBodyConverter<ErrorApi>(ErrorApi::class.java, arrayOfNulls(0))
            return try {
                converter.convert(response.errorBody()!!)
            } catch (e: IOException) {
                ErrorApi()
            }
        }
    }
}