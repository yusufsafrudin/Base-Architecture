package com.savr.baseandroid.local.data

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.savr.baseandroid.local.db.AppDatabase
import kotlinx.coroutines.*

class NoteRepository(application: Application) : ViewModel() {
    private val noteDao: NoteDao?
    private var notes: LiveData<List<NoteEntity>>? = null

    init {
        val db = AppDatabase.getInstance(application.applicationContext)
        noteDao = db?.noteDao()
        notes = noteDao?.getNotes()
    }

    fun getNotes(): LiveData<List<NoteEntity>>? {
        return notes
    }

    fun insert(note: NoteEntity) = CoroutineScope(Dispatchers.IO).launch {
        noteDao?.insertNote(note)
    }

    fun delete(note: NoteEntity) {
        viewModelScope.launch {
            noteDao?.deleteNote(note)
        }
    }

    fun update(note: NoteEntity) = runBlocking {
        this.launch(Dispatchers.IO) {
            noteDao?.updateNote(note)
        }
    }
}