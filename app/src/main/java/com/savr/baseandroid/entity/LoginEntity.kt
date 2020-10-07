package com.savr.baseandroid.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "login")
data class LoginEntity (
    @ColumnInfo(name = "username")
    var username: String,

    @ColumnInfo(name = "password")
    var password: String
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int? = null
}