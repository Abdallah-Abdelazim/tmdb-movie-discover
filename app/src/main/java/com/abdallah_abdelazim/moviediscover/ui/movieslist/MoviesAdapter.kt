package com.abdallah_abdelazim.moviediscover.ui.movieslist

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.abdallah_abdelazim.moviediscover.R
import com.abdallah_abdelazim.moviediscover.data.model.Movie
import com.abdallah_abdelazim.moviediscover.databinding.ItemMovieBinding

class MoviesAdapter(
    private var moviesList: List<Movie>? = null,
    private var itemClickListener: MovieItemClickListener
) : RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    private lateinit var inflater: LayoutInflater

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        if (!::inflater.isInitialized) inflater = LayoutInflater.from(parent.context)

        val binding: ItemMovieBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.item_movie,
            parent,
            false
        )
        binding.clickListener = itemClickListener

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.movie = moviesList?.get(position)
    }

    override fun getItemCount(): Int {
        return moviesList?.size ?: 0
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateMovies(moviesList: List<Movie>?) {
        if (this.moviesList === moviesList) return
        this.moviesList = moviesList
        notifyDataSetChanged()
    }

    class ViewHolder(internal val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root)

    /**
     * Used in handling movies items clicks
     */
    interface MovieItemClickListener {
        fun onMovieItemClick(movie: Movie)
    }

    companion object {
        private val TAG = MoviesAdapter::class.simpleName
    }
}