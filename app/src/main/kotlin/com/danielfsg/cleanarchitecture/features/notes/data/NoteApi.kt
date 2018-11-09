package com.danielfsg.cleanarchitecture.features.notes.data

import retrofit2.Call
import retrofit2.http.*

internal interface NoteApi {
    companion object {
        private const val NOTES = "notes/"
        private const val NOTES_MINE = "notes/mine"
        private const val PARAM_NOTE_ID = "noteID"
        private const val NOTE_BY_ID = "notes/{$PARAM_NOTE_ID}"
    }

    @GET(NOTES_MINE)
    fun getNotes(@Header("Authorization") bearer: String): Call<List<NoteEntity>>

    @GET(NOTE_BY_ID)
    fun getNoteById(@Header("Authorization") bearer: String, @Path(PARAM_NOTE_ID) noteID: String): Call<NoteEntity>

    @POST(NOTES)
    fun postNote(@Header("Authorization") bearer: String, @Body note: NoteEntity): Call<NoteEntity>

    @PUT(NOTE_BY_ID)
    fun putNote(@Header("Authorization") bearer: String, @Path(PARAM_NOTE_ID) noteID: String, @Body note: NoteEntity): Call<NoteEntity>

    @DELETE(NOTE_BY_ID)
    fun deleteNote(@Header("Authorization") bearer: String, @Path(PARAM_NOTE_ID) noteID: String): Call<NoteEntity>
}