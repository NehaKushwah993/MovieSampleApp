package com.nehak.moviesampleapp.ui.listing

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nehak.moviesampleapp.backend.models.Movie
import com.nehak.moviesampleapp.databinding.ListItemMovieBinding

/**
 * Created by Neha Kushwah on 3/24/21.
 */
class MoviesAdapter(private val context: Context) :
    RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {
    private val list: ArrayList<Movie>? = null

    class MovieViewHolder(
        private val context: Context,
        private val listItemMovieBinding: ListItemMovieBinding
    ) : RecyclerView.ViewHolder(listItemMovieBinding.root) {
        fun bind(movie: Movie) {
            listItemMovieBinding.tvTitle.text = movie.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val listItemMovieBinding: ListItemMovieBinding =
            ListItemMovieBinding.inflate(LayoutInflater.from(parent.context))
        return MovieViewHolder(context, listItemMovieBinding)
    }

    override fun getItemCount(): Int {
        if (list == null) return 0
        return list.size;
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(list!![position])
    }

    fun updateData(newList: List<Movie>) {
        list?.clear()
        val sortedList = newList.sortedBy { movie -> movie.popularity }
        list?.addAll(sortedList)
        notifyDataSetChanged()
    }
}