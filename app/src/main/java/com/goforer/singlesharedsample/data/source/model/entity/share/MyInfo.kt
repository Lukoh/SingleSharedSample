package com.goforer.singlesharedsample.data.source.model.entity.share

import com.goforer.singlesharedsample.data.source.model.entity.BaseEntity

data class MyInfo(val name: String,
                  val phone: String,
                  val email: String,
                  val address: String,
                  val postCode: String? = null,
                  val job: String? = null
) : BaseEntity() {

    internal fun isFilled(): Boolean {
        return name.isNotEmpty() &&
            phone.isNotEmpty() &&
            email.isNotEmpty() &&
            address.isNotEmpty()
    }
}