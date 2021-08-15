package com.iyaliyul.movieeyalapps.data.source

import com.iyaliyul.movieeyalapps.data.model.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService{

    @GET("discover/movie")
    suspend fun getDiscoverMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("sort_by") sortBy: String,
        @Query("include_adult") includeAdult: Boolean,
        @Query("page") page: Int,
        @Query("year") year: Int
    ) : MovieResponse
}