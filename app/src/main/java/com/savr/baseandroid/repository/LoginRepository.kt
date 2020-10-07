package com.savr.baseandroid.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.savr.baseandroid.entity.LoginEntity
import com.savr.baseandroid.local.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginRepository {

    companion object {
        var database: AppDatabase? = null

        var loginEntity: LiveData<LoginEntity>? = null

        private fun initDB(context: Context): AppDatabase {
            return AppDatabase.getInstance(context)
        }

        fun insertData(context: Context, username: String, password: String) {
            database = initDB(context)

            CoroutineScope(Dispatchers.IO).launch {
                val data = LoginEntity(username, password)
                database!!.loginDao().insertData(data)
            }
        }

        fun getDetails(context: Context, username: String) : LiveData<LoginEntity>? {
            database = initDB(context)

            loginEntity = database!!.loginDao().getDetail(username)

            return loginEntity
        }
    }
}