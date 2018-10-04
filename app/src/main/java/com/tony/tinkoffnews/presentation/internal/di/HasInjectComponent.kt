package com.tony.tinkoffnews.presentation.internal.di

interface HasInjectComponent<C> {

    fun getComponent(): C?
}