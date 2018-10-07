package com.tony.tinkoffnews.presentation.view.fragment

import android.support.v4.app.Fragment
import com.tony.tinkoffnews.presentation.internal.di.HasInjectComponent

abstract class BaseFragment : Fragment() {

    protected fun <C> getComponent(componentType: Class<C>): C? {
        return componentType.cast((activity as HasInjectComponent<C>).getComponent())
    }

}