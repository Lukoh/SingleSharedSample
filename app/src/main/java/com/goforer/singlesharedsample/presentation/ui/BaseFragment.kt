/*
 * Copyright (C) 2021 Lukoh Nam, goForer
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2 of the License,
 * or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with this program.
 * If not, see <http://www.gnu.org/licenses/>.
 */

package com.goforer.singlesharedsample.presentation.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.goforer.base.extension.gone
import com.goforer.base.extension.show
import com.goforer.base.extension.upShow
import com.goforer.base.utility.keyboard.BaseKeyboardObserver
import com.goforer.singlesharedsample.di.Injectable

abstract class BaseFragment<T : ViewBinding> : Fragment(), Injectable {
    private var _binding: T? = null
    abstract val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> T

    internal val binding
        get() = _binding as T

    protected lateinit var mainActivity: MainActivity
    private lateinit var context: Context

    override fun onAttach(context: Context) {
        super.onAttach(context)

        mainActivity = (context as MainActivity?)!!
        this.context = context
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = _binding ?: bindingInflater.invoke(inflater, container, false)

        return requireNotNull(_binding).root
    }

    override fun onResume() {
        super.onResume()

        mainActivity.addKeyboardListener()
    }

    override fun onPause() {
        mainActivity.removeKeyboardListener()
        hideKeyboard()

        super.onPause()
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }

    override fun onDestroy() {
        super.onDestroy()

        _binding = null
    }

    override fun getContext() = context

    protected open fun checkSession() {
    }

    protected fun setKeyboardStateRelatedViews(
        buttonBig: View?, buttonSmall: View?, onOpen: () -> Unit = {}
    ) {
        mainActivity.onKeyboardChange = {
            when (it) {
                BaseKeyboardObserver.KEYBOARD_INIT -> {
                    buttonSmall?.gone()
                    buttonBig?.show()
                }
                BaseKeyboardObserver.KEYBOARD_OPEN -> {
                    buttonBig?.gone()
                    buttonSmall?.upShow()
                    onOpen()
                }
                BaseKeyboardObserver.KEYBOARD_CLOSE -> {
                    buttonSmall?.gone()
                    buttonBig?.upShow()
                }
            }
        }
    }

    internal fun hideKeyboard() = mainActivity.hideKeyboard()

    internal fun hideKeyboardAndResetFocus(layout: ViewGroup) {
        hideKeyboard()
        layout.requestFocus()
    }
}