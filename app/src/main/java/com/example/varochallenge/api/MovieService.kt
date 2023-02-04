package com.example.varochallenge.api

import com.example.varochallenge.model.MovieList
import io.reactivex.Single
import retrofit2.http.GET

private const val apiKey = "7bfe007798875393b05c5aa1ba26323e"

interface MovieService {
    @GET("3/movie/now_playing?api_key=${apiKey}&language=en-US")
    fun getNowPlaying(): Single<MovieList>
}