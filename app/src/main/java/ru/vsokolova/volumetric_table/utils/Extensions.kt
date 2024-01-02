package ru.vsokolova.volumetric_table.utils

import android.view.View

fun View.alphaAnimate(endAlpha: Float, endAction: () -> Unit) {
    this.animate()
        .alpha(endAlpha)
        .setDuration(600)
        .withEndAction(endAction)
}