package com.danielfsg.cleanarchitecture.features.movies.presentation

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.danielfsg.cleanarchitecture.R
import com.danielfsg.cleanarchitecture.core.extension.inflate
import com.danielfsg.cleanarchitecture.core.extension.loadFromUrl
import com.danielfsg.cleanarchitecture.core.navigation.Navigator
import kotlinx.android.synthetic.main.item_movie.view.moviePoster
import kotlin.properties.Delegates

class MoviesAdapter : RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    internal var collection: List<MovieView> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    internal var clickListener: (MovieView, Navigator.Extras) -> Unit = { _, _ -> }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(parent.inflate(R.layout.item_movie))

    override fun getItemCount() = collection.size

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) =
        viewHolder.bind(collection[position], clickListener)

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movieView: MovieView, clickListener: (MovieView, Navigator.Extras) -> Unit) {
            itemView.moviePoster.loadFromUrl(movieView.poster)
            itemView.setOnClickListener { clickListener(movieView, Navigator.Extras(itemView.moviePoster)) }
        }

    }
}