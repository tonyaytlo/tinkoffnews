package com.tony.tinkoffnews.presentation.internal.di.components

import com.tony.tinkoffnews.presentation.internal.di.PerActivity
import com.tony.tinkoffnews.presentation.internal.di.modules.ActivityModule
import com.tony.tinkoffnews.presentation.internal.di.modules.NewsModule
import com.tony.tinkoffnews.presentation.view.MainActivity
import dagger.Component

@PerActivity
@Component(dependencies = [(ApplicationComponent::class)], modules = [(ActivityModule::class),
    (NewsModule::class)])
interface NewsComponent : ActivityComponent {

    fun inject(activity: MainActivity)
}