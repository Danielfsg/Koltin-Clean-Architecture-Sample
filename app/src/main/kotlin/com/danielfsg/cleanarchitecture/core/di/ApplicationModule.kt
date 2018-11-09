package com.danielfsg.cleanarchitecture.core.di

import com.danielfsg.cleanarchitecture.BuildConfig
import com.danielfsg.cleanarchitecture.core.navigation.Navigator
import com.danielfsg.cleanarchitecture.core.platform.NetworkHandler
import com.danielfsg.cleanarchitecture.core.platform.SharedPrefsHandler
import com.danielfsg.cleanarchitecture.features.authentication.data.UserService
import com.danielfsg.cleanarchitecture.features.authentication.domain.*
import com.danielfsg.cleanarchitecture.features.authentication.presentation.LoginViewModel
import com.danielfsg.cleanarchitecture.features.authentication.presentation.RegisterViewModel
import com.danielfsg.cleanarchitecture.features.movies.data.MoviesService
import com.danielfsg.cleanarchitecture.features.movies.domain.GetMovieDetails
import com.danielfsg.cleanarchitecture.features.movies.domain.GetMovies
import com.danielfsg.cleanarchitecture.features.movies.domain.MoviesRepository
import com.danielfsg.cleanarchitecture.features.movies.domain.PlayMovie
import com.danielfsg.cleanarchitecture.features.movies.presentation.MovieDetailsAnimator
import com.danielfsg.cleanarchitecture.features.movies.presentation.MovieDetailsViewModel
import com.danielfsg.cleanarchitecture.features.movies.presentation.MoviesAdapter
import com.danielfsg.cleanarchitecture.features.movies.presentation.MoviesViewModel
import com.danielfsg.cleanarchitecture.features.notes.data.NoteService
import com.danielfsg.cleanarchitecture.features.notes.domain.*
import com.danielfsg.cleanarchitecture.features.notes.presentation.list.NotesAdapter
import com.danielfsg.cleanarchitecture.features.notes.presentation.list.NotesViewModel
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://c713ec6f.eu.ngrok.io"

val applicationModule = module {

    factory { GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create() }

    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(get())
            .addConverterFactory(GsonConverterFactory.create(get()))
            .build()
    }

    single {
        val okHttpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC)
            okHttpClientBuilder.addInterceptor(loggingInterceptor)
        }
        okHttpClientBuilder.build() as OkHttpClient
    }

    single { Authenticator(get()) }
    single { Navigator(get()) }

    factory { NetworkHandler(get()) }
    factory { SharedPrefsHandler(get()) }

    factory { MoviesService(get()) }
    factory { UserService(get()) }
    factory { NoteService(get()) }

}

val loginModule = module {
    single { UserRepository.Network(get(), get()) as UserRepository }

    single { UserPrefs(get()) }

    single { LoginUser(get()) }
    viewModel { LoginViewModel(get(), get()) }

    single { RegisterUser(get()) }
    viewModel { RegisterViewModel(get(), get()) }
}

val notesModule = module {
    single { NoteRepository.Network(get(), get(),get()) as NoteRepository }
    single { NotePrefs(get()) }

    single { GetNotes(get()) }
    single { GetNoteById(get()) }
    single { AddNote(get()) }
    single { UpdateNote(get()) }
    single { DeleteNote(get()) }

    factory { NotesAdapter() }

    viewModel { NotesViewModel(get()) }

}

val moviesModule = module {
    single { MoviesRepository.Network(get(), get()) as MoviesRepository }
    factory { MoviesAdapter() }
    factory { MovieDetailsAnimator() }
    factory { PlayMovie(get(), get()) }

    single { GetMovies(get()) }
    viewModel { MoviesViewModel(get()) }

    single { GetMovieDetails(get()) }
    viewModel { MovieDetailsViewModel(get(), get()) }
}