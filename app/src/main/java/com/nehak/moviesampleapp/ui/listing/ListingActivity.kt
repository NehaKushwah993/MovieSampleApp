package com.nehak.moviesampleapp.ui.listing

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.nehak.moviesampleapp.backend.models.ResultApi
import com.nehak.moviesampleapp.databinding.ActivityListingBinding
import com.nehak.moviesampleapp.viewmodels.MovieListViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Neha Kushwah on 3/24/21.
 * Shows list of movie/show
 */
@AndroidEntryPoint
class ListingActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityListingBinding
    private val viewModel by viewModels<MovieListViewModel>()
    private lateinit var moviesAdapter: MoviesAdapter
    private lateinit var nowPlayingMoviesAdapter: MoviesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = ActivityListingBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        init()
        subscribeUi()
    }

    private fun init() {
        viewBinding.rvNowPlayingMovies.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        viewBinding.rvMovies.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        nowPlayingMoviesAdapter = MoviesAdapter(this)
        moviesAdapter = MoviesAdapter(this)

        viewBinding.rvNowPlayingMovies.adapter = nowPlayingMoviesAdapter
        viewBinding.rvMovies.adapter = moviesAdapter
    }

    private fun subscribeUi() {
        viewModel.movieList.observe(this, Observer { result ->

            when (result.status) {
                ResultApi.Status.SUCCESS -> {
                    result.data?.results?.let { list ->
                        moviesAdapter.updateData(list)
                    }
                    viewBinding.loading.visibility = View.GONE
                }

                ResultApi.Status.ERROR -> {
                    result.message?.let {
                        showError(it)
                    }
                    viewBinding.loading.visibility = View.GONE
                }

                ResultApi.Status.LOADING -> {
                    viewBinding.loading.visibility = View.VISIBLE
                }
            }

        })
    }

    private fun showError(msg: String) {
        Snackbar.make(viewBinding.vParent, msg, Snackbar.LENGTH_INDEFINITE).setAction("DISMISS") {
        }.show()
    }
}