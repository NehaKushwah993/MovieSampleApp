package com.nehak.moviesampleapp.backend.helper

import androidx.room.TypeConverter

/**
 * Created by Neha Kushwah on 3/24/21.
 */
class Converter {


    @TypeConverter
    fun listToString(values: List<Int>): String {
        val strList = mutableListOf<String>()
        values.forEach {
            strList.add(it.toString())
        }
        return strList.joinToString(",")
    }

    @TypeConverter
    fun stringToList(value: String): List<Int> {
        val intList = mutableListOf<Int>()
        value.split(",").forEach {
            intList.add(it.toInt())
        }
        return intList
    }


}