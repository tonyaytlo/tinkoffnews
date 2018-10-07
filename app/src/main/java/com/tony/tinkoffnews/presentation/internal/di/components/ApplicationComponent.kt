package com.tony.tinkoffnews.presentation.internal.di.components

import android.content.Context
import com.tony.tinkoffnews.domain.repository.NewsRepository
import com.tony.tinkoffnews.presentation.internal.di.modules.ApplicationModule
import com.tony.tinkoffnews.presentation.base.BaseActivity
import dagger.Component
import org.buffer.android.boilerplate.domain.executor.PostExecutionThread
import org.buffer.android.boilerplate.domain.executor.ThreadExecutor
import javax.inject.Singleton

@Singleton // Constraints this component to one-per-application or unscoped bindings.
@Component(modules = [(ApplicationModule::class)])
interface ApplicationComponent {

    fun inject(baseActivity: BaseActivity)

    //Exposed to sub-graphs.
    fun context(): Context

    fun threadExecutor(): ThreadExecutor
    fun postExecutionThread(): PostExecutionThread
    fun newsRepository(): NewsRepository
}
