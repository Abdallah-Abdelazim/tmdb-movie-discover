package com.abdallah_abdelazim.moviediscover.ui.moviedetails

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.abdallah_abdelazim.moviediscover.R
import com.abdallah_abdelazim.moviediscover.data.model.Movie
import com.abdallah_abdelazim.moviediscover.databinding.DialogMovieDetailsBinding
import com.abdallah_abdelazim.moviediscover.ui.moviedetails.MovieDetailsDialogFragment.Companion.newInstance
import com.abdallah_abdelazim.moviediscover.util.adjustDialogSize

/**
 * A [DialogFragment] that shows a [Movie] details.
 * Use [newInstance] to create instances of this class.
 */
class MovieDetailsDialogFragment : DialogFragment() {

    companion object {

        private val TAG = MovieDetailsDialogFragment::class.simpleName

        private const val ARG_MOVIE = "ARG_MOVIE"

        fun newInstance(movie: Movie): MovieDetailsDialogFragment {
            return MovieDetailsDialogFragment().apply {
                val args = Bundle();
                args.putParcelable(ARG_MOVIE, movie)
                arguments = args
            }
        }
    }

    private var _binding: DialogMovieDetailsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val viewModel: MovieDetailsDialogViewModel by viewModels()

    private val movie: Movie by lazy {
        arguments?.getParcelable(ARG_MOVIE)!!
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = DataBindingUtil.inflate(
            inflater, R.layout.dialog_movie_details, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = this
        binding.movie = movie
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));
        return dialog
    }

    override fun onResume() {
        super.onResume()

        adjustDialogSize(heightScreenRatioPortrait = 0.75f, widthScreenRatioLandscape = 0.6f)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}