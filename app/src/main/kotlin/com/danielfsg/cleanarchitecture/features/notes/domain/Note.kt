package com.danielfsg.cleanarchitecture.features.notes.domain

import android.os.Parcel
import com.danielfsg.cleanarchitecture.core.platform.KParcelable
import com.danielfsg.cleanarchitecture.core.platform.parcelableCreator
import com.danielfsg.cleanarchitecture.features.notes.data.NoteEntity
import java.util.*

data class Note (
    val id: String,
    val title: String,
    val content: String,
    val createdAt: Date? = null,
    val updatedAt: Date? = null
): KParcelable {
    fun toNoteEntity() = NoteEntity(title = title, content = content)


    companion object {
        @JvmField
        val CREATOR = parcelableCreator(::Note)
    }

    constructor(parcel: Parcel) : this(parcel.readString(), parcel.readString(), parcel.readString())

    override fun writeToParcel(dest: Parcel, flags: Int) {
        with(dest) {
            writeString(id)
            writeString(title)
            writeString(content)
        }
    }
}