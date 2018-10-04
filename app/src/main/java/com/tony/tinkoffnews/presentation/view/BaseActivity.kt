package com.tony.tinkoffnews.presentation.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.tony.tinkoffnews.NewsApp
import com.tony.tinkoffnews.presentation.internal.di.components.ApplicationComponent
import com.tony.tinkoffnews.presentation.internal.di.modules.ActivityModule

abstract class BaseActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.getApplicationComponent()?.inject(this)
    }

    protected fun getApplicationComponent(): ApplicationComponent? {
        return (application as NewsApp).getApplicationComponent()
    }

    protected fun getActivityModule(): ActivityModule {
        return ActivityModule(this)
    }

}