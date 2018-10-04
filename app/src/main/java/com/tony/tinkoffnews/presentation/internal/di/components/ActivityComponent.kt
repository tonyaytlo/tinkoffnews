package com.tony.tinkoffnews.presentation.internal.di.components

import android.app.Activity
import com.tony.tinkoffnews.presentation.internal.di.PerActivity
import com.tony.tinkoffnews.presentation.internal.di.modules.ActivityModule
import dagger.Component

@PerActivity
@Component(dependencies = [(ApplicationComponent::class)],
        modules = [ActivityModule::class])
interface ActivityComponent {
    //Exposed to sub-graphs.
    fun activity(): Activity
}
