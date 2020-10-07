package com.savr.baseandroid.di

import com.savr.baseandroid.data.AppDatabase
import org.koin.dsl.module

val dbModule = module {

    single { AppDatabase.getAppDatabase(get()) }

}