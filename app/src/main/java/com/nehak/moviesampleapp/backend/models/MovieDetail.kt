package com.nehak.moviesampleapp.backend.models

/**
 * Created by Neha Kushwah on 3/24/21.
 */
data class MovieDetail(

    val id: Int,
    val title: String,
    val overview: String,
    val poster_path: String,
    val genres: List<GenreSingle>

)