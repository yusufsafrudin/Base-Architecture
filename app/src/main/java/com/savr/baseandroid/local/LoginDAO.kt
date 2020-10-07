package com.savr.baseandroid.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.savr.baseandroid.entity.LoginEntity

@Dao
interface LoginDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(loginEntity: LoginEntity)

    @Query("SELECT * FROM login WHERE username=:username")
    fun getDetail(username: String?) : LiveData<LoginEntity>
}