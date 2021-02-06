package com.example.topmovies.ui.data

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.topmovies.R
import com.example.topmovies.api.Api
import com.example.topmovies.data.Movie
import com.example.topmovies.databinding.MovieItemBinding
import com.example.topmovies.ui.movies.MoviesFragment
import java.util.*
import kotlin.collections.ArrayList

class MovieAdapter(private val listener: OnItemClickListener, activity: MoviesFragment) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    private var moviesList = ArrayList<Movie>()
    private var displayList = ArrayList<Movie>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            MovieItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = displayList[position]
        holder.binding.textViewMovieName.text = movie.name
        //Use Glide to display the movie image
        //General directory for image from documentation https://image.tmdb.org/t/p/w500/fYtHxTxlhzD4QWfEbrC1rypysSD.jpg
        Glide.with(holder.itemView)
            .load(Api.IMAGE_URL + movie.image)
            .centerCrop()
            .transition(DrawableTransitionOptions.withCrossFade())
            .error(R.drawable.ic_baseline_error_24)
            .into(holder.binding.imageView)
    }

    override fun getItemCount() = displayList.size
    fun setData(newList: List<Movie>) {
        moviesList.addAll(newList)
        displayList.addAll(newList)
        notifyDataSetChanged()
    }

    fun search(newText: String) {
        if (newText.isNotEmpty()) {
            displayList.clear()
            val search = newText.toLowerCase(Locale.getDefault())
            moviesList.forEach {
                if (it.name?.toLowerCase(Locale.getDefault())?.contains(search) == true) {
                    displayList.add(it)
                }
            }
            notifyDataSetChanged()
        } else {
            displayList.clear()
            displayList.addAll(moviesList)
            notifyDataSetChanged()
        }
    }

    inner class MovieViewHolder(var binding: MovieItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener {
                val position = absoluteAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(displayList[position])
                }
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(movie: Movie)
    }
}

