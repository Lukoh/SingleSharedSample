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

package com.goforer.singlesharedsample.data.source.model.entity

import com.google.gson.Gson
import com.google.gson.GsonBuilder

open class BaseEntity {
    companion object {
        private fun gsonBuilder(): GsonBuilder {
            val builder = GsonBuilder()
            builder.serializeNulls()

            return builder
        }

        fun gson(): Gson {
            val builder = gsonBuilder()

            return builder.create()
        }

        fun <T> deepCopy(`object`: T, type: Class<T>): T? {
            return try {
                gson().fromJson(gson().toJson(`object`, type), type)
            } catch (e: Exception) {
                e.printStackTrace()

                null
            }
        }
    }
}