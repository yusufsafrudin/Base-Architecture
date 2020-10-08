package com.savr.baseandroid.local.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note")
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int? = null,

    @ColumnInfo(name = "note")
    var note: String
)