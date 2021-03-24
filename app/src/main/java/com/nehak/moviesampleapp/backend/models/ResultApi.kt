package com.nehak.moviesampleapp.backend.models

/**
 * Created by Neha Kushwah on 3/24/21.
 * Class for holding success response, error response and loading status
 */
data class ResultApi<out T>(val status: Status, val data: T?, val error: ErrorApi?, val message: String?) {

    enum class Status {
        SUCCESS,
        ERROR,
        LOADING
    }

    companion object {
        fun <T> success(data: T?): ResultApi<T> {
            return ResultApi(Status.SUCCESS, data, null, null)
        }

        fun <T> error(message: String, error: ErrorApi?): ResultApi<T> {
            return ResultApi(Status.ERROR, null, error, message)
        }

        fun <T> loading(data: T? = null): ResultApi<T> {
            return ResultApi(Status.LOADING, data, null, null)
        }
    }

    override fun toString(): String {
        return "Result(status=$status, data=$data, error=$error, message=$message)"
    }
}