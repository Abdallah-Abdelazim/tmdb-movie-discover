package com.abdallah_abdelazim.moviediscover.util

import android.content.Context
import android.content.res.Configuration


fun Context.getOrientation(): Int = resources.configuration.orientation

fun Context.isPortrait(): Boolean {
    return getOrientation() == Configuration.ORIENTATION_PORTRAIT
}

fun Context.isLandscape(context: Context): Boolean {
    return getOrientation() == Configuration.ORIENTATION_LANDSCAPE
}