package com.abdallah_abdelazim.moviediscover.util

import androidx.fragment.app.DialogFragment


/**
 * Call this method from DialogFragment#onResume callback to adjust the dialog size.
 * Adjusts dialog width & height to be proportional (ratio) to screen size.
 * For example, widthScreenRatioPortrait 0.8 means 80% of total screen width in portrait mode, etc...
 *
 * @param widthScreenRatioPortrait
 * @param heightScreenRatioPortrait
 * @param widthScreenRatioLandscape
 * @param heightScreenRatioLandscape
 */
fun DialogFragment.adjustDialogSize(
    widthScreenRatioPortrait: Float = 0.8f,
    heightScreenRatioPortrait: Float = 0.6f,
    widthScreenRatioLandscape: Float = 0.5f,
    heightScreenRatioLandscape: Float = 0.9f
) {
    val totalWidth = resources.displayMetrics.widthPixels
    val totalHeight = resources.displayMetrics.heightPixels
    context?.let {
        if (it.isPortrait()) {
            dialog?.window?.setLayout(
                (totalWidth * widthScreenRatioPortrait).toInt(),
                (totalHeight * heightScreenRatioPortrait).toInt()
            )
        } else {
            dialog?.window?.setLayout(
                (totalWidth * widthScreenRatioLandscape).toInt(),
                (totalHeight * heightScreenRatioLandscape).toInt()
            )
        }
    }
}
