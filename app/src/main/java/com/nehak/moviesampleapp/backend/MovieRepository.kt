package com.nehak.moviesampleapp.backend

import com.nehak.moviesampleapp.backend.local.MovieDao
import com.nehak.moviesampleapp.backend.models.ResponseNowPlayingMovies
import com.nehak.moviesampleapp.backend.models.ResultApi
import com.nehak.moviesampleapp.backend.remote.MovieRemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 * Created by Neha Kushwah on 3/24/21.
 *
 * Repository which fetches data from Remote or Local data sources
 */
class MovieRepository @Inject constructor(
    private val movieRemoteDataSource: MovieRemoteDataSource,
    private val movieDao: MovieDao
) {

    suspend fun fetchNowPlayingMovies(): Flow<ResultApi<ResponseNowPlayingMovies>?> {
        return flow {
            emit(fetchTrendingMoviesCached())
            emit(ResultApi.loading())
            val result = movieRemoteDataSource.fetchNowPlayingMovies()

            //Cache to database if response is successful
            if (result.status == ResultApi.Status.SUCCESS) {
                result.data?.results?.let { it ->
                    movieDao.deleteAll(it)
                    movieDao.insertAll(it)
                }
            }
            emit(result)
        }.flowOn(Dispatchers.IO)
    }

    private fun fetchTrendingMoviesCached(): ResultApi<ResponseNowPlayingMovies>? =
        movieDao.getAllMovies()?.let {
            ResultApi.success(ResponseNowPlayingMovies(it))
        }

}