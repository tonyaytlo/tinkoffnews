package com.tony.tinkoffnews.presentation.view

import android.os.Bundle
import com.tony.tinkoffnews.R
import com.tony.tinkoffnews.domain.interactors.GetNewsList
import com.tony.tinkoffnews.presentation.internal.di.components.DaggerNewsComponent
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject
    lateinit var getNewsList: GetNewsList

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeInjector()
    }

    private fun initializeInjector() {
        DaggerNewsComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build()
                .inject(this)
    }

}
