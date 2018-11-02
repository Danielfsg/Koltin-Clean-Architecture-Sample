package com.danielfsg.cleanarchitecture.features.notes.data

import com.danielfsg.cleanarchitecture.core.extension.empty
import com.danielfsg.cleanarchitecture.features.notes.domain.Note
import java.util.*

data class NoteEntity(
    val id: String? = null,
    val title: String,
    val content: String,
    val createdAt: Date? = null,
    val updatedAt: Date? = null
) {

    companion object {
        fun empty() = NoteEntity(String.empty(), String.empty(), String.empty(), null, null)
    }

    fun toNote() = Note(id, title, content, createdAt, updatedAt)
}