package com.savr.baseandroid.network

class UtilsApi {

    companion object{
        const val BASE_URL = "https://api.themoviedb.org/3/"
        const val BASE_POSTER_URL = "https://image.tmdb.org/t/p/w500"
        const val API_KEY = "e9906e4a57e2cf19f54dcba5a135d47f"
        const val LANGUAGE = "en-US"
    }

    private val service = RetrofitClient.getClient(BASE_URL)?.create(BaseApiService::class.java)

    suspend fun getDataMovie()= service?.getListMovie(API_KEY, LANGUAGE)?.results
    suspend fun getSearchMovie(query : String)= service?.searchMovie(API_KEY, LANGUAGE, query)?.results

}