package com.nehak.moviesampleapp.viewmodels

import androidx.lifecycle.*
import com.nehak.moviesampleapp.backend.MovieRepository
import com.nehak.moviesampleapp.backend.models.ResponseNowPlayingMovies
import com.nehak.moviesampleapp.backend.models.ResultApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Neha Kushwah on 3/24/21.
 */
@HiltViewModel
class MovieListViewModel @Inject constructor(private val movieRepository: MovieRepository) :
    ViewModel() {

    private val _movieList = MutableLiveData<ResultApi<ResponseNowPlayingMovies>>()
    val movieList = _movieList

    init {
        fetchMovies()
    }

    private fun fetchMovies() {
        viewModelScope.launch {
            movieRepository.fetchNowPlayingMovies().collect {
                _movieList.value = it
            }
        }
    }
}