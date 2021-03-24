package com.nehak.moviesampleapp.backend.remote.network

import com.nehak.moviesampleapp.backend.models.MovieDetail
import com.nehak.moviesampleapp.backend.models.ResponseNowPlayingMovies
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by Neha Kushwah on 3/24/21.
 */
interface MovieAPIs {

    @GET("/3/trending/movie/week")
    suspend fun getNowPlayingMovies() : Response<ResponseNowPlayingMovies>
}