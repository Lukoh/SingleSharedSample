package com.goforer.singlesharedsample.di.module

import android.app.Application
import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * A module for Android-specific dependencies which require a [Context] or
 * [android.app.Application] to create.
 */
@Module
class AppModule {
    companion object {
        const val timeout_read = 30L
        const val timeout_connect = 30L
        const val timeout_write = 30L
    }

    @Provides
    @Singleton
    fun appContext(application: Application): Context = application.applicationContext

    @Provides
    @Singleton
    fun provideGSon(): Gson = GsonBuilder().create()

}