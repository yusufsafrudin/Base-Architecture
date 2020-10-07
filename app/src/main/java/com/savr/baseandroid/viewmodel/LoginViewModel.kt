package com.savr.baseandroid.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.savr.baseandroid.entity.LoginEntity
import com.savr.baseandroid.repository.LoginRepository

class LoginViewModel : ViewModel() {

    var dataLogin: LiveData<LoginEntity>? = null

    fun insertData(context: Context, username: String, password: String) {
        LoginRepository.insertData(context, username, password)
    }

    fun getDetail(context: Context, username: String): LiveData<LoginEntity>? {
        dataLogin = LoginRepository.getDetails(context, username)
        return dataLogin
    }
}