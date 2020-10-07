package com.savr.baseandroid.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.savr.baseandroid.R
import com.savr.baseandroid.utils.replaceFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        replaceFragment(R.id.frameMain, MovieFragment.newInstance(), false )
    }
}
