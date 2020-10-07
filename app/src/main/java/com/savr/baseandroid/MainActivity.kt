package com.savr.baseandroid

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.savr.baseandroid.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var loginViewModel: LoginViewModel
    lateinit var context: Context
    lateinit var username: String
    lateinit var password: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        btnAddLogin.setOnClickListener {
            username = txtUsername.text.toString().trim()
            password = txtPassword.text.toString().trim()

            when {
                username.isEmpty() -> {
                    txtUsername.error = "Please enter username"
                }
                password.isEmpty() -> {
                    txtPassword.error = " Please enter password"
                }
                else -> {
                    loginViewModel.insertData(this, username, password)
                    lblInsertResponse.text = "Insert Successfully"
                }
            }
        }

        btnReadLogin.setOnClickListener {
            username = txtUsername.text.toString().trim()

            loginViewModel.getDetail(this, username)!!.observe(this, Observer {
                if (it == null) {
                    lblReadResponse.text = "Data not found !!"
                    lblUseraname.text = "- - -"
                    lblPassword.text = "- - -"
                } else {
                    lblUseraname.text = it.username
                    lblPassword.text = it.password
                    lblReadResponse.text = "Data found"
                }
            })
        }
    }
}
