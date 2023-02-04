package com.example.varochallenge.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.get
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.varochallenge.R
import com.example.varochallenge.databinding.MovieFeedBinding
import com.example.varochallenge.model.MovieInfo
import com.example.varochallenge.viewmodel.MovieViewModel

class MovieFragment : Fragment(), MovieAdapter.OnItemClickListener {

    companion object {
        const val TAG = "MovieFragment"
    }

    private lateinit var movieViewModel: MovieViewModel
    private lateinit var list: MutableLiveData<List<MovieInfo>>
    private var _binding: MovieFeedBinding? = null
    private val binding get() = _binding!!
    private var filterFavorites = true
    private val favoritesText = "Favorites"
    private val nowPlayingText = "Now Playing"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        movieViewModel = ViewModelProvider(this).get(MovieViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MovieFeedBinding.inflate(inflater)
        binding.movieListRecyclerview.layoutManager = GridLayoutManager(context, 2)
        binding.favoritesButtonFilter.text = favoritesText

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Would be used to switch to results between favorites and now playing
        // by switching lists in view model
        binding.favoritesButtonFilter.setOnClickListener {
            if(filterFavorites)
                binding.favoritesButtonFilter.text = nowPlayingText
            else
                binding.favoritesButtonFilter.text = favoritesText

            filterFavorites = !filterFavorites
        }

        list = movieViewModel.getMoviesList()
        list.observe(viewLifecycleOwner) { movies ->
            binding.movieListRecyclerview.adapter = MovieAdapter(movies, this)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    // This function would be used to add into favorites section.
    override fun onItemClick(position: Int) {
        val movies = list.value
        Toast.makeText(context, "${movies?.get(position)?.title} added to favorites!",
            Toast.LENGTH_LONG).show()
    }
}