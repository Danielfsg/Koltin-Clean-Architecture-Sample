package com.danielfsg.cleanarchitecture.features.notes.presentation.note

import android.content.Context
import android.content.Intent
import com.danielfsg.cleanarchitecture.core.platform.BaseActivity
import com.danielfsg.cleanarchitecture.features.notes.domain.Note

class AddEditNoteActivity : BaseActivity() {

    companion object {
        private const val INTENT_EXTRA_PARAM_MOVIE = "com.danielfsg.INTENT_PARAM_MOVIE"

        fun callingIntent(context: Context, note:Note): Intent {
            val intent = Intent(context, AddEditNoteFragment::class.java)
            intent.putExtra(INTENT_EXTRA_PARAM_MOVIE, note)
            return intent
        }
    }

    override fun fragment() = AddEditNoteFragment()
}