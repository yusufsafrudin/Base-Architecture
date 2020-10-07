package com.savr.baseandroid.data.movie.remote.response

import com.google.gson.annotations.SerializedName

class MovieResponse (
    @SerializedName("results") val results : List<MovieItem>
)