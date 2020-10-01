package com.savr.baseandroid.utils
sealed class ProgressLoading {
    object LOADING : ProgressLoading()
    object ERROR : ProgressLoading()
    object DONE : ProgressLoading()
}