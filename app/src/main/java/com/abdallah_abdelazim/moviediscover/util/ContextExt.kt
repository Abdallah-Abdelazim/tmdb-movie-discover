package com.abdallah_abdelazim.moviediscover.util

import android.content.Context
import android.content.res.Configuration


val Context.orientation: Int
    get() = resources.configuration.orientation

fun Context.isPortrait(): Boolean {
    return orientation == Configuration.ORIENTATION_PORTRAIT
}

fun Context.isLandscape(context: Context): Boolean {
    return orientation == Configuration.ORIENTATION_LANDSCAPE
}