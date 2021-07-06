package com.goforer.sharedatasample.di.component

import android.app.Application
import com.goforer.singlesharedsample.SingleSharedSample
import com.goforer.singlesharedsample.di.module.AppModule
import com.goforer.singlesharedsample.di.module.activity.MainActivityModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class, AppModule::class, MainActivityModule::class
    ]
)

interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance app: Application): AppComponent
    }

    fun inject(shareDataSample: SingleSharedSample)
}