package com.abdallah_abdelazim.moviediscover.data.source.remote

import com.abdallah_abdelazim.moviediscover.data.model.Movie
import com.abdallah_abdelazim.moviediscover.data.source.MoviesRepository
import io.reactivex.rxjava3.core.Single

class MoviesRemoteRepository : MoviesRepository {

    override fun fetchPopularMovies(): Single<List<Movie>> {
        return RetrofitClient.moviesService.getPopularMovies().map { response ->
            response.results
        }
    }
}