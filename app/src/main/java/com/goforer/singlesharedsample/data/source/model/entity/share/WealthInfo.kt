package com.goforer.singlesharedsample.data.source.model.entity.share

import com.goforer.singlesharedsample.data.source.model.entity.BaseEntity

data class WealthInfo(
    val type: String,
    val item: String,
    val amount: String,
    val currency: String
) : BaseEntity()