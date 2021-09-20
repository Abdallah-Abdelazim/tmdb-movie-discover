package com.abdallah_abdelazim.moviediscover.data.source

import com.abdallah_abdelazim.moviediscover.data.model.Movie
import io.reactivex.rxjava3.core.Single

interface MoviesRepository {

    fun fetchPopularMovies(): Single<List<Movie>>
}