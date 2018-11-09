package com.danielfsg.cleanarchitecture.features.notes.domain

import com.danielfsg.cleanarchitecture.features.notes.data.NoteEntity
import java.util.*

data class Note(
    val id: String? = null,
    val title: String,
    val content: String,
    val createdAt: Date? = null,
    val updatedAt: Date? = null
) {
    fun toNoteEntity() = NoteEntity(title = title, content = content)
}