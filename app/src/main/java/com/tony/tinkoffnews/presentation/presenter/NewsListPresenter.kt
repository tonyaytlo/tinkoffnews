package com.tony.tinkoffnews.presentation.presenter

import com.tony.tinkoffnews.data.entity.NewsItem
import com.tony.tinkoffnews.domain.interactors.GetNewsList
import com.tony.tinkoffnews.presentation.base.BasePresenter
import io.reactivex.observers.DisposableSingleObserver
import javax.inject.Inject

class NewsListPresenter @Inject
constructor(private val getNewsList: GetNewsList) : BasePresenter<NewsListContract.View>(),
        NewsListContract.Presenter {

    override fun onResume() {
    }

    override fun onDestroy() {
        getNewsList.dispose()
    }

    fun setView(view: NewsListContract.View?) {
        this.view = view
        loadNews()
    }

    override fun fetchNews() {
        view?.showLoading()
        getNewsList.execute(NewsDisposable(), GetNewsList.Params(true))
    }

    override fun loadNews() {
        view?.showLoading()
        getNewsList.execute(NewsDisposable())
    }

    inner class NewsDisposable : DisposableSingleObserver<List<NewsItem>>() {
        override fun onSuccess(news: List<NewsItem>) {
            view?.hideLoading()
            view?.setNews(news)
        }

        override fun onError(e: Throwable) {
            view?.hideLoading()
            view?.showError(e.localizedMessage)
        }
    }
}