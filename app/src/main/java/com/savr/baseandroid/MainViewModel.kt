package com.savr.baseandroid

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.savr.baseandroid.entity.Movie
import com.savr.baseandroid.network.UtilsApi
import com.savr.baseandroid.utils.ProgressLoading
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val mMovie = MutableLiveData<List<Movie>>()
    private val mProgress = MutableLiveData<ProgressLoading>()

    val listMovie : LiveData<List<Movie>>
        get() = mMovie

    val progress : LiveData<ProgressLoading>
        get() = mProgress

    fun getMovies() {
        mProgress.value = ProgressLoading.LOADING
        viewModelScope.launch {
            try {
                val response = UtilsApi().getDataMovie()
                mMovie.value = response!!
                mProgress.value = ProgressLoading.DONE
            } catch (e: Throwable) {
                mProgress.value = ProgressLoading.ERROR
                e.printStackTrace()
            }
        }
    }

    fun searchMovie(query: String) {
        mProgress.value = ProgressLoading.LOADING
        viewModelScope.launch {
            try {
                val response = UtilsApi().getSearchMovie(query)
                mMovie.value = response!!
                mProgress.value = ProgressLoading.DONE
            } catch (e: Throwable) {
                mProgress.value = ProgressLoading.ERROR
                e.printStackTrace()
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}