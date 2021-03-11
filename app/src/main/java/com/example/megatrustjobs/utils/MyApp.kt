package com.example.megatrustjobs.utils

import android.app.Application
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import com.example.megatrustjobs.homeDi
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApp : Application(), ViewModelStoreOwner {
    private val appViewModelStore: ViewModelStore by lazy {
        ViewModelStore()
    }

    override fun onCreate() {
        super.onCreate()


        startKoin {
            androidContext(this@MyApp)
            modules(
                listOf(
                    homeDi
                )
            )
        }
    }

    override fun getViewModelStore(): ViewModelStore {
        return appViewModelStore
    }

}