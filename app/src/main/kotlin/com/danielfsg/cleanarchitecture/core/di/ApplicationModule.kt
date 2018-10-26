package com.danielfsg.cleanarchitecture.core.di

import com.danielfsg.cleanarchitecture.BuildConfig
import com.danielfsg.cleanarchitecture.core.navigation.Navigator
import com.danielfsg.cleanarchitecture.core.platform.NetworkHandler
import com.danielfsg.cleanarchitecture.features.login.Authenticator
import com.danielfsg.cleanarchitecture.features.movies.data.MoviesService
import com.danielfsg.cleanarchitecture.features.movies.domain.GetMovieDetails
import com.danielfsg.cleanarchitecture.features.movies.domain.GetMovies
import com.danielfsg.cleanarchitecture.features.movies.domain.MoviesRepository
import com.danielfsg.cleanarchitecture.features.movies.domain.PlayMovie
import com.danielfsg.cleanarchitecture.features.movies.presentation.MovieDetailsAnimator
import com.danielfsg.cleanarchitecture.features.movies.presentation.MovieDetailsViewModel
import com.danielfsg.cleanarchitecture.features.movies.presentation.MoviesAdapter
import com.danielfsg.cleanarchitecture.features.movies.presentation.MoviesViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.architecture.ext.viewModel
import org.koin.dsl.module.applicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val applicationModule = applicationContext {
    bean {
        Retrofit.Builder()
            .baseUrl("https://raw.githubusercontent.com/android10/Sample-Data/master/Android-CleanArchitecture-Kotlin/")
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    bean {
        val okHttpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC)
            okHttpClientBuilder.addInterceptor(loggingInterceptor)
        }
        okHttpClientBuilder.build() as OkHttpClient
    }

    factory { NetworkHandler(get()) }
    factory { MoviesService(get()) }

    bean { Authenticator() }
    bean { Navigator(get()) }

}

val moviesModule = applicationContext {
    bean { MoviesRepository.Network(get(), get()) as MoviesRepository }
    factory { MoviesAdapter() }
    factory { MovieDetailsAnimator() }
    bean { GetMovies(get()) }
    viewModel { MoviesViewModel(get()) }

    bean { GetMovieDetails(get()) }
    factory { PlayMovie(get(), get()) }
    viewModel { MovieDetailsViewModel(get(), get()) }

}