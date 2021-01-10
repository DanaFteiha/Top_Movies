package com.example.topmovies.ui.Fragments

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.topmovies.R
import com.example.topmovies.api.Api
import kotlinx.android.synthetic.main.fragment_details.*

class DetailsFragment : Fragment(R.layout.fragment_details) {
    private val args by navArgs<DetailsFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val movie = args.movie

        text_view_title.text = movie.name.toString()
        text_view_description.text = movie.overview
        text_view_rating.text = "Rating: " +movie.voteAvg.toString()

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
                    progress_bar.isVisible = false
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    progress_bar.isVisible = false
                    text_view_rating.isVisible = true
                    text_view_title.isVisible = true
                    text_view_description.isVisible = true
                    return false
                }
            })
            .into(image_view)

    }
}