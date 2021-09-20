package com.abdallah_abdelazim.moviediscover.util

import com.abdallah_abdelazim.moviediscover.data.Constants

object TmdbUtils {

    @JvmStatic
    fun getImageUrl(imagePath: String): String = Constants.TMDB_IMAGES_BASE_URL + imagePath
}