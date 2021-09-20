package com.abdallah_abdelazim.moviediscover.util

import androidx.fragment.app.DialogFragment

// Declare dialogs width & height to be proportional to screen size
// for example width 0.8 means 80% of total screen width
val DialogFragment.DIALOG_STD_WIDTH_SCREEN_RATIO_PORTRAIT: Float
    get() = 0.8f
val DialogFragment.DIALOG_STD_HEIGHT_SCREEN_RATIO_PORTRAIT: Float
    get() = 0.6f
val DialogFragment.DIALOG_STD_WIDTH_SCREEN_RATIO_LANDSCAPE: Float
    get() = 0.5f
val DialogFragment.DIALOG_STD_HEIGHT_SCREEN_RATIO_LANDSCAPE: Float
    get() = 0.9f

/**
 * Call this method from DialogFragment#onResume callback to adjust the dialog size correctly.
 * Adjusts dialog width & height to be proportional (ratio) to screen size.
 * For example, widthScreenRatioPortrait 0.8 means 80% of total screen width in portrait mode, etc...
 *
 * @param widthScreenRatioPortrait
 * @param heightScreenRatioPortrait
 * @param widthScreenRatioLandscape
 * @param heightScreenRatioLandscape
 */
/**
 * Call this method from DialogFragment#onResume callback to adjust the dialog size correctly.
 *
 * @param dialogFragment
 */
fun DialogFragment.adjustDialogSize(
    widthScreenRatioPortrait: Float = DIALOG_STD_WIDTH_SCREEN_RATIO_PORTRAIT,
    heightScreenRatioPortrait: Float = DIALOG_STD_HEIGHT_SCREEN_RATIO_PORTRAIT,
    widthScreenRatioLandscape: Float = DIALOG_STD_WIDTH_SCREEN_RATIO_LANDSCAPE,
    heightScreenRatioLandscape: Float = DIALOG_STD_HEIGHT_SCREEN_RATIO_LANDSCAPE
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
