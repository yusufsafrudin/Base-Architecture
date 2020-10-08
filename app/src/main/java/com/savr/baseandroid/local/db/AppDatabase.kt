package com.savr.baseandroid.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.savr.baseandroid.local.data.NoteDao
import com.savr.baseandroid.local.data.NoteEntity

@Database(entities = [NoteEntity::class], exportSchema = false, version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao

    companion object {

        private const val DB_NAME = "NOTE_DB"
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase? {
            if (instance == null) {
                synchronized(AppDatabase::class) {
                    instance = Room
                        .databaseBuilder(
                            context,
                            AppDatabase::class.java,
                            DB_NAME
                        )
                        .build()
                }
            }
            return instance
        }

    }
}