package com.savr.baseandroid.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.savr.baseandroid.entity.LoginEntity

@Database(entities = [LoginEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun loginDao(): LoginDAO

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if (INSTANCE !=null) return INSTANCE!!

            synchronized(this) {
                INSTANCE = Room
                    .databaseBuilder(context, AppDatabase::class.java, "app-database")
                    .fallbackToDestructiveMigration()
                    .build()

                return INSTANCE!!
            }
        }
    }
}