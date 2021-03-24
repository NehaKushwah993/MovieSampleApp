package com.nehak.moviesampleapp.backend.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.nehak.moviesampleapp.backend.helper.Converter
import com.nehak.moviesampleapp.backend.models.Movie

/**
 * Created by Neha Kushwah on 3/24/21.
 */
@Database(entities = [Movie::class], version = 1)
@TypeConverters(Converter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}