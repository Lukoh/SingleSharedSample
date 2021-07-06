package com.goforer.singlesharedsample.di.module.fragment

import com.goforer.singlesharedsample.presentation.ui.profile.MyInfoFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MyInfoFragmentModule {
    @ContributesAndroidInjector
    internal abstract fun contributeMyInfoFragment(): MyInfoFragment
}