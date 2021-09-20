package com.abdallah_abdelazim.moviediscover.ui.movieslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.abdallah_abdelazim.moviediscover.R
import com.abdallah_abdelazim.moviediscover.data.model.Movie
import com.abdallah_abdelazim.moviediscover.databinding.FragmentMoviesListBinding
import com.abdallah_abdelazim.moviediscover.ui.moviedetails.MovieDetailsDialogFragment
import com.abdallah_abdelazim.moviediscover.util.showSnackbar

/**
 * A [Fragment] showing a list of movies sorted by popularity.
 */
class MoviesListFragment : Fragment(), MoviesAdapter.MovieItemClickListener {

    private var _binding: FragmentMoviesListBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val viewModel: MoviesListViewModel by viewModels()

    private lateinit var moviesAdapter: MoviesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_movies_list, container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupMoviesRecyclerView()

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        viewModel.popularMovies.observe(viewLifecycleOwner, { popularMoviesList ->
            moviesAdapter.updateMovies(popularMoviesList)
        })

        viewModel.messageEvent.observe(viewLifecycleOwner, { msgStrResId ->
            showSnackbar(msgStrResId)
        })

        viewModel.movieDetailsNavigationEvent.observe(viewLifecycleOwner, { movie ->
            openMovieDetailsDialog(movie)
        })
    }

    private fun setupMoviesRecyclerView() {
        binding.rvMovies.setHasFixedSize(true)

        moviesAdapter = MoviesAdapter(itemClickListener = this)
        binding.rvMovies.adapter = moviesAdapter
    }

    private fun openMovieDetailsDialog(movie: Movie) {
        val movieDetailsDialogFragment = MovieDetailsDialogFragment.newInstance(movie)
        movieDetailsDialogFragment.show(childFragmentManager, null)
    }

    override fun onMovieItemClick(movie: Movie) {
        viewModel.openMovieDetails(movie)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}