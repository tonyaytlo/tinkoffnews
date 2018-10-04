package com.tony.tinkoffnews.presentation.internal.di.modules

import android.app.Activity
import com.tony.tinkoffnews.presentation.internal.di.PerActivity
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private val activity: Activity) {

    @Provides
    @PerActivity
    internal fun activity(): Activity {
        return this.activity
    }
}
