package com.nehak.moviesampleapp.backend.remote

import com.nehak.moviesampleapp.backend.helper.ErrorUtils
import com.nehak.moviesampleapp.backend.models.ResponseNowPlayingMovies
import com.nehak.moviesampleapp.backend.models.ResultApi
import com.nehak.moviesampleapp.backend.remote.network.MovieAPIs
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

/**
 * Created by Neha Kushwah on 3/24/21.
 *
 * fetches data from remote source
 */
class MovieRemoteDataSource @Inject constructor(private val retrofit: Retrofit) {

    suspend fun fetchNowPlayingMovies(): ResultApi<ResponseNowPlayingMovies> {
        val movieService = retrofit.create(MovieAPIs::class.java);
        return getResponse(
            request = { movieService.getNowPlayingMovies() },
            defaultErrorMessage = "Error fetching Movie list")

    }

    private suspend fun <T> getResponse(request: suspend () -> Response<T>, defaultErrorMessage: String): ResultApi<T> {
        return try {
            val result = request.invoke()
            if (result.isSuccessful) {
                return ResultApi.success(result.body())
            } else {
                val errorResponse = ErrorUtils.parseError(result, retrofit)
                ResultApi.error(errorResponse?.status_message ?: defaultErrorMessage, errorResponse)
            }
        } catch (e: Throwable) {
            ResultApi.error("Unknown Error", null)
        }
    }
}