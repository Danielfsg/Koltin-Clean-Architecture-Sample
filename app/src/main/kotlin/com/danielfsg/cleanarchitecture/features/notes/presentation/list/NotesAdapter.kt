package com.danielfsg.cleanarchitecture.features.notes.presentation.list

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.danielfsg.cleanarchitecture.R
import com.danielfsg.cleanarchitecture.core.extension.inflate
import com.danielfsg.cleanarchitecture.features.notes.domain.Note
import kotlinx.android.synthetic.main.item_note.view.*
import kotlin.properties.Delegates

class NotesAdapter : RecyclerView.Adapter<NotesAdapter.ViewHolder>() {

    internal var collection: List<Note> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    internal var clickListener: (Note) -> Unit = { }

    override fun getItemCount() = collection.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(parent.inflate(R.layout.item_note))


    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(collection[position], clickListener)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(note: Note, clickListener: (Note) -> Unit) {
            itemView.note_card.setOnClickListener { clickListener(note) }

            itemView.tv_note_title.text = note.title
            itemView.tv_note_content.text = note.content
        }
    }
}