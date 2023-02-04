package com.example.varochallenge.model

data class MovieList(
    val dates: Dates,
    val page: Int,
    val results: List<MovieInfo>,
    val total_pages: Int,
    val total_results: Int
)