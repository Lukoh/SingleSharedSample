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