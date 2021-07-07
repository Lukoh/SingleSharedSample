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

package com.goforer.singlesharedsample.presentation.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

open class SharedViewModel<T> : ViewModel() {
    private var sharedData: SharedFlow<T> = MutableSharedFlow()

    internal fun share(data: T) {
        sharedData = flow {
            emit(data)
        }.shareIn (
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            replay = 1
        )
    }

    internal fun shared(doOnResult: (data: T) -> Unit) {
        viewModelScope.launch {
            sharedData.collect {
               doOnResult(it)
           }
        }
    }
}