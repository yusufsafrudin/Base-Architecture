package com.savr.baseandroid.base

import com.savr.baseandroid.di.dbModule
import com.savr.baseandroid.di.movieModule
import org.koin.core.module.Module

class MainApplication : BaseApplication() {

    override fun getDefinedModules(): List<Module> {
        return listOf(
            dbModule,
            movieModule
        )
    }
}