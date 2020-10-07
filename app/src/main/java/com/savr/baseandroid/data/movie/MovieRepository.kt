package com.savr.baseandroid.data.movie

import com.savr.baseandroid.data.movie.remote.MovieApiInterface
import com.savr.baseandroid.data.movie.remote.response.MovieResponse
import io.reactivex.Observable

class MovieRepository(private val services: MovieApiInterface) :
    MovieDataSource {

    override fun getPopularMovie(): Observable<MovieResponse> {
        return services.getPopularMovie()
    }

}