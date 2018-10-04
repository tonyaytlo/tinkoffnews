package com.tony.tinkoffnews.presentation.internal.di

import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy.RUNTIME
import javax.inject.Scope


@Scope
@Retention(RUNTIME)
annotation class PerActivity
