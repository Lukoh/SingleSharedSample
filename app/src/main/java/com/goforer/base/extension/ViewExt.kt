package com.goforer.base.extension

import android.animation.AnimatorListenerAdapter
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

fun View.show(): View {
    if (visibility != View.VISIBLE) {
        visibility = View.VISIBLE
    }
    return this
}

/**
 * Hide the view. (visibility = View.INVISIBLE)
 */
fun View.hide(): View {
    if (visibility != View.INVISIBLE) {
        visibility = View.INVISIBLE
    }
    return this
}

/**
 * Remove the view (visibility = View.GONE)
 */
fun View.gone(): View {
    if (visibility != View.GONE) {
        visibility = View.GONE
    }
    return this
}

fun View.upShow(duration: Long = 300) {
    show()
    translationY = height.toFloat()
    animate()
        .setDuration(duration)
        .translationY(0f)
        .setListener(object : AnimatorListenerAdapter() {
        })
        .start()
}

/**
 * Extension method to show a keyboard for View.
 */
fun View.showKeyboard() {
    if (this.requestFocus()) {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(this, 0)
    }
}

/**
 * Try to hide the keyboard and returns whether it worked
 * https://stackoverflow.com/questions/1109022/close-hide-the-android-soft-keyboard
 */
fun View.hideKeyboard(): Boolean {
    runCatching {
        val inputMethodManager =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        return inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
    }.onFailure { e ->
        e.printStackTrace()
    }

    this.clearFocus()

    return false
}