package com.goforer.singlesharedsample

import android.app.Activity
import android.app.Application
import android.content.ComponentCallbacks2
import android.content.Context
import android.content.res.Configuration
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ProcessLifecycleOwner
import androidx.multidex.MultiDex
import com.goforer.singlesharedsample.di.helper.AppInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import timber.log.Timber
import javax.inject.Inject
import kotlin.system.exitProcess

open class SingleSharedSample : Application(), LifecycleObserver, HasAndroidInjector {
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    internal var isInForeground = false

    companion object {
        fun exitApplication(activity: Activity) {
            runCatching {
                activity.finishAndRemoveTask()
                exit()
            }.onFailure {
                exit()
            }
        }

        private fun exit() {
            android.os.Process.killProcess(android.os.Process.myPid())
            System.runFinalizersOnExit(true)
            exitProcess(0)
        }
    }

    var activity: Activity? = null
    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.uprootAll()
            Timber.plant(Timber.DebugTree())
        }

        ProcessLifecycleOwner.get().lifecycle.addObserver(this)
        registerComponentCallbacks(ComponentCallback(this))
        AppInjector.init(this)
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)

        MultiDex.install(this)
    }

    override fun androidInjector() = dispatchingAndroidInjector

    class ComponentCallback(private val app: SingleSharedSample) : ComponentCallbacks2 {
        override fun onConfigurationChanged(newConfig: Configuration) {}

        override fun onTrimMemory(level: Int) {
            if (level == ComponentCallbacks2.TRIM_MEMORY_UI_HIDDEN) {
                app.isInForeground = true
            }
        }

        override fun onLowMemory() {
            Timber.d("onLowMemory")
        }
    }
}