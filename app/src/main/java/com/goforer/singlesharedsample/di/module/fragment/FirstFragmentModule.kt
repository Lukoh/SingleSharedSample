package com.goforer.singlesharedsample.di.module.fragment

import com.goforer.singlesharedsample.presentation.ui.profile.FirstFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FirstFragmentModule {
    @ContributesAndroidInjector
    internal abstract fun contributeFirstFragment(): FirstFragment
}