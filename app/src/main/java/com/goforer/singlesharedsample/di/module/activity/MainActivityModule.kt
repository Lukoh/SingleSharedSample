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

package com.goforer.singlesharedsample.di.module.activity

import com.goforer.singlesharedsample.di.module.fragment.FirstFragmentModule
import com.goforer.singlesharedsample.di.module.fragment.MyInfoFragmentModule
import com.goforer.singlesharedsample.di.module.fragment.MyWealthFragmentModule
import com.goforer.singlesharedsample.presentation.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityModule {
    @ContributesAndroidInjector(
        modules = [
            //sign up
            FirstFragmentModule::class,
            MyInfoFragmentModule::class,
            MyWealthFragmentModule::class
        ]
    )
    abstract fun contributeMainActivity(): MainActivity
}