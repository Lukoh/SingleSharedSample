package com.goforer.singlesharedsample.di.module.fragment

import com.goforer.singlesharedsample.presentation.ui.profile.MyWealthFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MyWealthFragmentModule {
    @ContributesAndroidInjector
    internal abstract fun contributeMyWealthFragment(): MyWealthFragment
}