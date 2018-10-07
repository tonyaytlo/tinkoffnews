package com.tony.tinkoffnews.presentation.presenter

import com.tony.tinkoffnews.data.entity.NewsItem

interface NewsListContract {

    interface View {
        fun showLoading()

        fun hideLoading()

        fun setNews(news: List<NewsItem>)

        fun showError(msg: String)
    }

    interface Presenter {
        fun fetchNews()

        fun loadNews()
    }

}