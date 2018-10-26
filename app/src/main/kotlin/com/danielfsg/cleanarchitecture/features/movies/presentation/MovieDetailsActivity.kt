package com.danielfsg.cleanarchitecture.features.movies.presentation

import android.content.Context
import android.content.Intent
import com.danielfsg.cleanarchitecture.core.platform.BaseActivity

class MovieDetailsActivity : BaseActivity() {

    companion object {
        private const val INTENT_EXTRA_PARAM_MOVIE = "com.danielfsg.INTENT_PARAM_MOVIE"

        fun callingIntent(context: Context, movie: MovieView): Intent {
            val intent = Intent(context, MovieDetailsActivity::class.java)
            intent.putExtra(INTENT_EXTRA_PARAM_MOVIE, movie)
            return intent
        }
    }

    override fun fragment() = MovieDetailsFragment.forMovie(intent.getParcelableExtra(INTENT_EXTRA_PARAM_MOVIE))

}