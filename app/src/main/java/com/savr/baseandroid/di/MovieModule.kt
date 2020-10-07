package com.savr.baseandroid.di

import com.savr.baseandroid.data.movie.MovieDataSource
import com.savr.baseandroid.data.movie.MovieRepository
import com.savr.baseandroid.data.movie.remote.MovieApiInterface
import com.savr.baseandroid.ui.MovieViewModel
import com.savr.baseandroid.utils.Network
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.create

val movieModule = module {

    single<MovieApiInterface> { Network.builder().create() }

    single<MovieDataSource> { MovieRepository(get()) }

    viewModel { MovieViewModel(get()) }
}