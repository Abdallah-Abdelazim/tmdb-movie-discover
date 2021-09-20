package com.abdallah_abdelazim.moviediscover.data.source.remote

import com.abdallah_abdelazim.moviediscover.data.Constants
import com.abdallah_abdelazim.moviediscover.data.model.TmdbMoviesResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface MoviesService {

    @GET("discover/movie?sort_by=popularity.desc&api_key=${Constants.TMDB_API_KEY}")
    fun getPopularMovies(): Single<TmdbMoviesResponse>
}