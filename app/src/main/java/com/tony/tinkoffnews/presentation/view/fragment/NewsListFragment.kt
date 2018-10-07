package com.tony.tinkoffnews.presentation.view.fragment

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import com.tony.tinkoffnews.R
import com.tony.tinkoffnews.data.entity.NewsItem
import com.tony.tinkoffnews.presentation.internal.di.components.NewsComponent
import com.tony.tinkoffnews.presentation.presenter.NewsListContract
import com.tony.tinkoffnews.presentation.presenter.NewsListPresenter
import com.tony.tinkoffnews.presentation.view.adapter.NewsAdapter
import ru.galt.app.extensions.bind
import ru.galt.app.extensions.makeTransactionAdd
import javax.inject.Inject

class NewsListFragment : BaseFragment(), NewsListContract.View {

    companion object {
        const val TAG = "NewsListFragment"
    }

    private val srNews by bind<SwipeRefreshLayout>(R.id.srNews)
    private val rvNews by bind<RecyclerView>(R.id.rvNews)


    @Inject
    lateinit var presenter: NewsListPresenter

    private var adapter: NewsAdapter? = null
    private var toastError: Toast? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_news_list, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getComponent(NewsComponent::class.java)?.inject(this)
        srNews.setColorSchemeColors(ContextCompat.getColor(activity!!, R.color.colorPrimary))
        initListeners()
        presenter.setView(this)

    }

    private fun initListeners() {
        srNews.setOnRefreshListener {
            presenter.fetchNews()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.onDestroy()
    }

    override fun showLoading() {
        srNews.isRefreshing = true
    }

    override fun hideLoading() {
        srNews.isRefreshing = false
    }

    override fun setNews(news: List<NewsItem>) {
        if (rvNews.adapter == null) {
            adapter = NewsAdapter(activity!!, news.toMutableList())
            adapter!!.getOnClickSubject()
                    .subscribe {
                        openNewsContent(it.id)
                    }
            rvNews.layoutManager = LinearLayoutManager(activity!!)
            rvNews.layoutAnimation = AnimationUtils.loadLayoutAnimation(activity!!, R.anim.layout_anim_fall_down)
            rvNews.adapter = adapter

        } else {
            adapter?.addAll(news.toMutableList())
            rvNews.scheduleLayoutAnimation()
        }
    }

    override fun showError(msg: String) {
        toastError?.cancel()
        toastError = Toast.makeText(activity, msg, Toast.LENGTH_SHORT)
        toastError?.show()
    }

    private fun openNewsContent(id: String) {
        makeTransactionAdd(R.id.container, NewsContentFragment.getInstance(id), NewsContentFragment.TAG, true)
    }
}