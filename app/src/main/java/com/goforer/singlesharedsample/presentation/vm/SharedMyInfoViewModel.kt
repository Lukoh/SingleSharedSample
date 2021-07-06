package com.goforer.singlesharedsample.presentation.vm

import com.goforer.singlesharedsample.data.source.model.entity.share.MyInfo
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedMyInfoViewModel
@Inject
constructor() : SharedViewModel<MyInfo?>()