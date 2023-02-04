package com.example.varochallenge.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.varochallenge.api.MovieServiceInstance.imageUrl
import com.example.varochallenge.databinding.MovieItemBinding
import com.example.varochallenge.model.MovieInfo
import com.squareup.picasso.Picasso

class MovieAdapter(var movies: List<MovieInfo>,
                   var listener: OnItemClickListener) :
    RecyclerView.Adapter<MovieAdapter.MovieItemViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieItemViewHolder {
        return MovieItemViewHolder(MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MovieItemViewHolder, position: Int) {
        with(holder){
            binding.movieTitle.text = movies[position].title
            Picasso.get().load(imageUrl + movies[position].poster_path)
                .into(binding.movieImage)

        }
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    inner class MovieItemViewHolder(val binding: MovieItemBinding) : RecyclerView.ViewHolder(binding.root),

        View.OnClickListener {
        init {
            //Instead of on single click, would add for double click logic to add
            // to favorites on double click
            binding.movieImage.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if(position != RecyclerView.NO_POSITION)
                listener.onItemClick(position)
        }
    }
    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}
