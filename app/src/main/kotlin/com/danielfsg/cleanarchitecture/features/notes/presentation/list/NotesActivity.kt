package com.danielfsg.cleanarchitecture.features.notes.presentation.list

import android.content.Context
import android.content.Intent
import android.view.Menu
import com.danielfsg.cleanarchitecture.R
import com.danielfsg.cleanarchitecture.core.platform.BaseActivity

class NotesActivity : BaseActivity() {

    companion object {
        fun callingIntent(context: Context): Intent {
            val intent = Intent(context, NotesActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            return intent
        }
    }

    override fun fragment() = NotesFragment()

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.notes_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

}