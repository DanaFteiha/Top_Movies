package com.example.topmovies.ui.details

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.topmovies.R
import com.example.topmovies.api.Api
import com.example.topmovies.data.Movie
import com.example.topmovies.databinding.FragmentDetailsBinding
import com.example.topmovies.di.AppModule
import com.example.topmovies.di.DaggerMainComponent
import javax.inject.Inject
import javax.inject.Named


class DetailsFragment : Fragment(R.layout.fragment_details) {
    @Inject
    @field:Named("details")
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: DetailsViewModel
    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private val args by navArgs<DetailsFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        DaggerMainComponent.builder().appModule(AppModule(requireContext())).build().inject1(this)
        val movieID = args.movieID
        viewModel = ViewModelProvider(this, viewModelFactory).get(DetailsViewModel::class.java)
        viewModel.findMovieByID(movieID = movieID)
        viewModel.movies.observe(viewLifecycleOwner, Observer { response ->
            setData(response[0])
        })
    }

    private fun setData(movie: Movie) {
        binding.textViewTitle.text = movie.name
        binding.textViewDescription.text = movie.overview
        binding.textViewRating.text = "Rating: " + movie.voteAvg.toString()
        //Use Glide to display the movie image
        //General directory for image from documentation https://image.tmdb.org/t/p/w500
        Glide.with(this@DetailsFragment)
            .load(Api.IMAGE_URL + movie.image)
            .error(R.drawable.ic_baseline_error_24)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    binding.progressBar.isVisible = false
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    binding.progressBar.isVisible = false
                    binding.textViewRating.isVisible = true
                    binding.textViewTitle.isVisible = true
                    binding.textViewDescription.isVisible = true
                    return false
                }
            })
            .into(binding.imageView)
    }
}