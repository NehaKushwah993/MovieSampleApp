package com.nehak.moviesampleapp.backend.local

import androidx.room.*
import com.nehak.moviesampleapp.backend.models.Movie

/**
 * Created by Neha Kushwah on 3/24/21.
 */
@Dao
interface MovieDao {

    @Query("SELECT * FROM movie")
    fun getAllMovies(): List<Movie>?


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(movies: List<Movie>)

    @Delete
    fun delete(movie: Movie)

    @Delete
    fun deleteAll(movie: List<Movie>)
}