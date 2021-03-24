package com.nehak.moviesampleapp.backend.models

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Neha Kushwah on 3/24/21.
 */
@Entity
data class Movie(
    @NonNull
    @PrimaryKey
    val id: Int,
    val title: String?,
    val overview: String?,
    val popularity: Double,
    val poster_path: String,
    val genre_ids: List<Int>,
    val isNowPlaying: Boolean
)