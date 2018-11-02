package com.danielfsg.cleanarchitecture.core.navigation

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.app.FragmentActivity
import android.view.View
import android.widget.ImageView
import com.danielfsg.cleanarchitecture.core.extension.empty
import com.danielfsg.cleanarchitecture.features.authentication.domain.Authenticator
import com.danielfsg.cleanarchitecture.features.authentication.presentation.LoginActivity
import com.danielfsg.cleanarchitecture.features.authentication.presentation.RegisterActivity
import com.danielfsg.cleanarchitecture.features.movies.presentation.MovieDetailsActivity
import com.danielfsg.cleanarchitecture.features.movies.presentation.MovieView
import com.danielfsg.cleanarchitecture.features.movies.presentation.MoviesActivity

class Navigator(private val authenticator: Authenticator) {

    private fun showLogin(context: Context) = context.startActivity(LoginActivity.callingIntent(context))

    fun showRegister(context: Context) = context.startActivity(RegisterActivity.callingIntent(context))

    fun showMain(context: Context) {
        when (authenticator.isUserLoggedIn()) {
            true -> showMovies(context)
            false -> showLogin(context)
        }
    }

    fun showMovies(context: Context) = context.startActivity(MoviesActivity.callingIntent(context))

    fun showMovieDetails(activity: FragmentActivity, movie: MovieView, navigationExtras: Extras) {
        val intent = MovieDetailsActivity.callingIntent(activity, movie)
        val sharedView = navigationExtras.transitionSharedElement as ImageView
        val activityOptions = ActivityOptionsCompat
            .makeSceneTransitionAnimation(activity, sharedView, sharedView.transitionName)
        activity.startActivity(intent, activityOptions.toBundle())
    }

    private val VIDEO_URL_HTTP = "http://www.youtube.com/watch?v="
    private val VIDEO_URL_HTTPS = "https://www.youtube.com/watch?v="

    fun openVideo(context: Context, videoUrl: String) {
        try {
            context.startActivity(createYoutubeIntent(videoUrl))
        } catch (ex: ActivityNotFoundException) {
            context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(videoUrl)))
        }
    }

    private fun createYoutubeIntent(videoUrl: String): Intent {
        val videoId = when {
            videoUrl.startsWith(VIDEO_URL_HTTP) -> videoUrl.replace(VIDEO_URL_HTTP, String.empty())
            videoUrl.startsWith(VIDEO_URL_HTTPS) -> videoUrl.replace(VIDEO_URL_HTTPS, String.empty())
            else -> videoUrl
        }

        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:$videoId"))
        intent.putExtra("for_fullscreen", true)
        return intent
    }

    class Extras(val transitionSharedElement: View)

}