package com.savr.baseandroid.data.movie

import com.savr.baseandroid.data.movie.remote.response.MovieResponse
import io.reactivex.Observable

interface MovieDataSource {

    fun getPopularMovie(): Observable<MovieResponse>

}