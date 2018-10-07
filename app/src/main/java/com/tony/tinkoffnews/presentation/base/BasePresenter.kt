package com.tony.tinkoffnews.presentation.base


abstract class BasePresenter<V> constructor(protected var view: V? = null) {

    abstract fun onResume()

    abstract fun onDestroy()

}