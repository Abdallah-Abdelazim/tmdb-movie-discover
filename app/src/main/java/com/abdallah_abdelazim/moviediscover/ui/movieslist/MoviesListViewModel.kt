package com.abdallah_abdelazim.moviediscover.ui.movieslist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.abdallah_abdelazim.moviediscover.R
import com.abdallah_abdelazim.moviediscover.data.model.Movie
import com.abdallah_abdelazim.moviediscover.data.source.MoviesRepository
import com.abdallah_abdelazim.moviediscover.data.source.remote.MoviesRemoteRepository
import com.abdallah_abdelazim.moviediscover.util.SingleLiveEvent
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MoviesListViewModel : ViewModel() {

    private val moviesRepository: MoviesRepository by lazy {
        MoviesRemoteRepository()
    }

    private val _popularMovies by lazy {
        MutableLiveData<List<Movie>>().also {
            fetchPopularMovies()
        }
    }
    val popularMovies: LiveData<List<Movie>> get() = _popularMovies

    private val _isLoadingEvent by lazy {
        MutableLiveData<Boolean>()
    }
    val isLoadingEvent: LiveData<Boolean> get() = _isLoadingEvent

    val messageEvent by lazy {
        SingleLiveEvent<Int>()
    }

    val movieDetailsNavigationEvent by lazy {
        SingleLiveEvent<Movie>()
    }

    private var moviesLoadDisposable: Disposable? = null

    private fun fetchPopularMovies() {
        Log.d(TAG, "fetchPopularMovies")

        _isLoadingEvent.value = true

        moviesLoadDisposable = moviesRepository.fetchPopularMovies()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { popularMoviesList, error ->
                if (error != null || popularMoviesList == null) {
                    Log.e(TAG, "fetchPopularMovies error", error)
                    messageEvent.value = R.string.error_network_request
                } else {
                    _popularMovies.value = popularMoviesList
                }

                _isLoadingEvent.value = false
            }
    }

    fun openMovieDetails(movie: Movie) {
        movieDetailsNavigationEvent.value = movie
    }

    override fun onCleared() {
        super.onCleared()
        moviesLoadDisposable?.dispose()
    }

    companion object {
        private val TAG = MoviesListViewModel::class.simpleName
    }
}