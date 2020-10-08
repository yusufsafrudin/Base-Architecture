package com.savr.baseandroid.local.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NoteDao {
    @Query("Select * from note")
    fun getNotes(): LiveData<List<NoteEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(noteEntity: NoteEntity)

    @Update
    suspend fun updateNote(noteEntity: NoteEntity)

    @Delete
    suspend fun deleteNote(noteEntity: NoteEntity)
}