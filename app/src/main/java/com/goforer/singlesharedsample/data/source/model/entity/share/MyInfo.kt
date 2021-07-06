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