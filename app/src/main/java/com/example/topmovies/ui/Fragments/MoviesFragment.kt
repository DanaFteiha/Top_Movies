package com.example.topmovies.ui.Fragments

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.annotation.Nullable
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.topmovies.R
import com.example.topmovies.Room.MovieDataBase
import com.example.topmovies.api.Api
import com.example.topmovies.data.Movie
import com.example.topmovies.di.AppModule
import com.example.topmovies.di.DaggerMainComponent
import com.example.topmovies.ui.MovieAdapter
import com.example.topmovies.ui.MovieViewModel
import kotlinx.android.synthetic.main.fragment_movies.*
import javax.inject.Inject


class MoviesFragment : Fragment(R.layout.fragment_movies), MovieAdapter.OnItemClickListener {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: MovieAdapter
    private lateinit var viewModel: MovieViewModel
    private lateinit var moviesApi: Api
    private lateinit var db: MovieDataBase
    private val TAG = "Movies Fragment"

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Here notify the fragment that it should participate in options menu handling.
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupRecyclerView()
        // val component = DaggerMainComponent.create().inject(this)
        DaggerMainComponent.builder().appModule(AppModule(requireContext())).build().inject(this)

        getData()

    }

    private fun setupRecyclerView() {
        viewAdapter = MovieAdapter(this, this)
        recyclerView = movieRecyclerView.apply {
            setHasFixedSize(true)
            // specify an viewAdapter
            adapter = viewAdapter
        }
    }

    //A function to create the view Model as observe its results
    private fun getData() {
        viewModel = ViewModelProvider(this, viewModelFactory).get(MovieViewModel::class.java)
        viewModel.isLoading.observe(viewLifecycleOwner, Observer {
            if (it) {
                showProgressBar()
            } else hideProgressBar()
        })
        viewModel.moviesResponse.observe(viewLifecycleOwner, Observer { response ->
            //observe is loading
            viewAdapter.setData(response)

        })
    }

    override fun onItemClick(movie: Movie) {
        //Forward a movie parcelable object using safeArgs(compile time safe)
        val actionClick = MoviesFragmentDirections.actionMoviesFragmentToDetailsFragment(movie)
        findNavController().navigate(actionClick)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        // First clear current all the menu items
        menu.clear()
        // Add the new menu items
        inflater.inflate(R.menu.movie_search, menu)
        super.onCreateOptionsMenu(menu, inflater)
        val menuItem = menu!!.findItem(R.id.search)
        if (menuItem != null) {
            val searchView = menuItem.actionView as? SearchView
            searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    viewAdapter.search(newText.orEmpty())
                    return true
                }
            })
        }
        return super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }

    //The reference is null when the view is destroyed
    override fun onDestroyView() {
        super.onDestroyView()
    }

    private fun hideProgressBar() {
        progress_bar.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        progress_bar.visibility = View.VISIBLE
    }
}

