package com.savr.baseandroid.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.savr.baseandroid.data.movie.local.MovieDao
import com.savr.baseandroid.data.movie.local.response.MovieFavorite
import com.savr.baseandroid.data.movie.remote.response.MovieItem

@Database(entities = [MovieFavorite::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    companion object {

        private var INSTANCE: AppDatabase? = null

        fun getAppDatabase(context: Context): AppDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room
                    .databaseBuilder(context.applicationContext, AppDatabase::class.java, "db-android")
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return INSTANCE!!
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }

    abstract fun movieDao(): MovieDao

}