package com.goforer.base.utility.keyboard

import com.goforer.singlesharedsample.presentation.ui.MainActivity

class KeyboardObserver(activity: MainActivity) : BaseKeyboardObserver(activity) {
    fun addListener(action: (Int) -> Unit) {
        addListener(object : OnKeyboardListener {
            override fun onKeyboardChange(status: Int) {
                action(status)
            }
        })
    }
}