package com.savr.baseandroid.network

import com.savr.baseandroid.entity.MovieEntity
import retrofit2.http.GET
import retrofit2.http.Query

interface BaseApiService {
    @GET("discover/movie")
    suspend fun getListMovie(
        @Query("api_key") api_key:String,
        @Query("language")language:String
    ) : MovieEntity

    @GET("search/movie")
    suspend fun  searchMovie(
        @Query("api_key") api_key:String,
        @Query("language")language:String,
        @Query("query")query:String
    ) : MovieEntity

}