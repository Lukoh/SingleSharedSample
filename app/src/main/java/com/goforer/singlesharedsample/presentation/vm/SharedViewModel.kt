package com.goforer.singlesharedsample.presentation.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goforer.base.extension.isNull
import kotlinx.coroutines.flow.*

open class SharedViewModel<T> : ViewModel() {
    private var sharedData: SharedFlow<T>? = null

    internal fun share(data: T) {
        sharedData = flow {
            emit(data)
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = data
        )
    }

    internal fun shared(doOnResult: (data: T?) -> Unit) {
        sharedData.isNull({
            doOnResult(null)
        }, {
            flow {
                emit(it.collectLatest { data ->
                    doOnResult(data)
                })
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.Eagerly,
                initialValue = it
            )
        })
    }
}