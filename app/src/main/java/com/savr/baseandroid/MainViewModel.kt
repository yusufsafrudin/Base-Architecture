package com.savr.baseandroid

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.savr.baseandroid.local.data.NoteEntity
import com.savr.baseandroid.local.data.NoteRepository

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private var noteRepository = NoteRepository(application)
    private var notes: LiveData<List<NoteEntity>>? = noteRepository.getNotes()

    fun insertNote(note: NoteEntity) {
        noteRepository.insert(note)
    }

    fun getNotes(): LiveData<List<NoteEntity>>? {
        return notes
    }

    fun deleteNote(note: NoteEntity) {
        noteRepository.delete(note)
    }

    fun updateNote(note: NoteEntity) {
        noteRepository.update(note)
    }

}