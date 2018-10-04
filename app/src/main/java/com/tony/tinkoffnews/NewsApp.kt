package com.tony.tinkoffnews

import android.app.Application
import com.tony.tinkoffnews.presentation.internal.di.components.ApplicationComponent
import com.tony.tinkoffnews.presentation.internal.di.components.DaggerApplicationComponent
import com.tony.tinkoffnews.presentation.internal.di.modules.ApplicationModule

class NewsApp : Application() {

    private var applicationComponent: ApplicationComponent? = null

    override fun onCreate() {
        super.onCreate()
        this.initializeInjector()
    }

    private fun initializeInjector() {
        this.applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .build()
    }

    fun getApplicationComponent(): ApplicationComponent? {
        return this.applicationComponent
    }
}