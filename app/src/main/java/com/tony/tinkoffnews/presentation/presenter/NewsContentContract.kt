package com.tony.tinkoffnews.presentation.presenter

import com.tony.tinkoffnews.data.entity.NewsContent

interface NewsContentContract {

    interface View {
        fun showLoading()

        fun hideLoading()

        fun setNewsContent(content: NewsContent)

        fun showError(msg: String)
    }

    interface Presenter {
        fun getNewsContent(id: String)
    }
}