package com.goforer.singlesharedsample.presentation.vm

import com.goforer.singlesharedsample.data.source.model.entity.share.WealthInfo
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedWealthViewModel
@Inject
constructor() : SharedViewModel<WealthInfo?>()