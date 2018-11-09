package com.danielfsg.cleanarchitecture.features.notes.domain

import com.danielfsg.cleanarchitecture.core.exception.Failure
import com.danielfsg.cleanarchitecture.core.functional.Either
import com.danielfsg.cleanarchitecture.core.platform.NetworkHandler
import com.danielfsg.cleanarchitecture.features.notes.data.NoteEntity
import com.danielfsg.cleanarchitecture.features.notes.data.NoteService

interface NoteRepository {

    fun getAllNotes(): Either<Failure, List<Note>>
    fun getNoteByID(noteID: String): Either<Failure, Note>
    fun addNote(note: Note): Either<Failure, Note>
    fun updateNote(noteID: String, note: Note): Either<Failure, Note>
    fun deleteNote(noteID: String): Either<Failure, Note>

    class Network(
        private val networkHandler: NetworkHandler,
        private val service: NoteService,
        prefs: NotePrefs
    ) : NoteRepository {
        private val jwToken = prefs.jwToken

        override fun getAllNotes(): Either<Failure, List<Note>> {
            return when (networkHandler.isConnected) {
                true -> {
                    networkHandler.request(service.getNotes(jwToken!!), { it -> it.map { it.toNote() } }, emptyList())
                }
                false, null -> Either.Left(Failure.NetworkConnection())
            }
        }

        override fun getNoteByID(noteID: String): Either<Failure, Note> {
            return when (networkHandler.isConnected) {
                true -> {
                    networkHandler.request(service.getNoteById(jwToken!!, noteID), { it.toNote() }, NoteEntity.empty())
                }
                false, null -> Either.Left(Failure.NetworkConnection())
            }
        }

        override fun addNote(note: Note): Either<Failure, Note> {
            return when (networkHandler.isConnected) {
                true -> {
                    networkHandler.request(
                        service.postNote(jwToken!!, note.toNoteEntity()),
                        { it.toNote() },
                        NoteEntity.empty()
                    )
                }
                false, null -> Either.Left(Failure.NetworkConnection())
            }
        }

        override fun updateNote(noteID: String, note: Note): Either<Failure, Note> {
            return when (networkHandler.isConnected) {
                true -> {
                    networkHandler.request(
                        service.putNote(jwToken!!, noteID, note.toNoteEntity()),
                        { it.toNote() },
                        NoteEntity.empty()
                    )
                }
                false, null -> Either.Left(Failure.NetworkConnection())
            }
        }

        override fun deleteNote(noteID: String): Either<Failure, Note> {
            return when (networkHandler.isConnected) {
                true -> {
                    networkHandler.request(
                        service.deleteNote(jwToken!!, noteID),
                        { it.toNote() },
                        NoteEntity.empty()
                    )
                }
                false, null -> Either.Left(Failure.NetworkConnection())
            }
        }
    }
}