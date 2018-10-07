package com.tony.tinkoffnews.presentation.view.activity

import android.os.Bundle
import com.tony.tinkoffnews.R
import com.tony.tinkoffnews.presentation.base.BaseActivity
import com.tony.tinkoffnews.presentation.internal.di.HasInjectComponent
import com.tony.tinkoffnews.presentation.internal.di.components.DaggerNewsComponent
import com.tony.tinkoffnews.presentation.internal.di.components.NewsComponent
import com.tony.tinkoffnews.presentation.view.fragment.NewsListFragment
import ru.galt.app.extensions.makeTransactionAdd


class MainActivity : BaseActivity(), HasInjectComponent<NewsComponent> {

    private var newsComponent: NewsComponent? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeInjector()
        if (supportFragmentManager.findFragmentByTag(NewsListFragment.TAG) == null) {
            openNewsListFragment()
        }
    }

    private fun initializeInjector() {
        newsComponent = DaggerNewsComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build()
    }

    override fun getComponent() = newsComponent

    private fun openNewsListFragment() {
        makeTransactionAdd(R.id.container, NewsListFragment(), NewsListFragment.TAG)
    }
}


