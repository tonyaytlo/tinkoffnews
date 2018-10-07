package com.tony.tinkoffnews.presentation.presenter

import com.tony.tinkoffnews.data.entity.NewsContent
import com.tony.tinkoffnews.domain.interactors.GetNewsContent
import io.reactivex.observers.DisposableSingleObserver
import javax.inject.Inject

class NewsContentPresenter
@Inject constructor(private val getNewsContent: GetNewsContent)
    : BasePresenter<NewsContentContract.View>(), NewsContentContract.Presenter {

    var newsId: String? = null

    override fun onResume() {

    }

    override fun onDestroy() {
        getNewsContent.dispose()
        view = null
    }

    fun setView(view: NewsContentContract.View) {
        this.view = view
        loadData()
    }

    private fun loadData() {
        if (!newsId.isNullOrEmpty()) {
            getNewsContent(newsId!!)
        } else {
            view?.showError("Illegal newsId")
        }
    }

    override fun getNewsContent(id: String) {
        view?.showLoading()
        getNewsContent.execute(NewsContentDisposable(), GetNewsContent.Params(id))
    }


    inner class NewsContentDisposable : DisposableSingleObserver<NewsContent>() {
        override fun onSuccess(content: NewsContent) {
            view?.hideLoading()
            view?.setNewsContent(content)
        }

        override fun onError(e: Throwable) {
            view?.hideLoading()
            view?.showError(e.localizedMessage)
        }
    }


}