package com.tony.tinkoffnews.presentation.view.fragment

import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.tony.tinkoffnews.R
import com.tony.tinkoffnews.data.entity.NewsContent
import com.tony.tinkoffnews.extension.formatToDate
import com.tony.tinkoffnews.extension.makeGone
import com.tony.tinkoffnews.extension.makeVisible
import com.tony.tinkoffnews.extension.textFromHtml
import com.tony.tinkoffnews.presentation.base.BaseFragment
import com.tony.tinkoffnews.presentation.internal.di.components.NewsComponent
import com.tony.tinkoffnews.presentation.presenter.NewsContentContract
import com.tony.tinkoffnews.presentation.presenter.NewsContentPresenter
import ru.galt.app.extensions.bind
import ru.galt.app.presentation.views.LoadingView
import javax.inject.Inject

class NewsContentFragment : BaseFragment(), NewsContentContract.View {

    companion object {
        const val TAG = "NewsContentFragment"
        private const val ARG_ID = "id"

        fun getInstance(id: String) = NewsContentFragment()
                .apply {
                    arguments = Bundle().apply { putString(ARG_ID, id) }
                }
    }

    private val id by lazy { arguments?.getString(ARG_ID) }

    @Inject
    lateinit var presenter: NewsContentPresenter

    private val tvTitle by bind<TextView>(R.id.tvTitle)
    private val tvContent by bind<TextView>(R.id.tvContent)
    private val tvDate by bind<TextView>(R.id.tvDate)
    private val loading by bind<LoadingView>(R.id.loading)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getComponent(NewsComponent::class.java).inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_news_content, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvContent.movementMethod = LinkMovementMethod.getInstance()
        presenter.newsId = id
        presenter.setView(this)

    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    override fun showLoading() {
        loading.makeVisible()
    }

    override fun hideLoading() {
        loading.makeGone()
    }

    override fun setNewsContent(content: NewsContent) {
        tvTitle.text = content.title.name
        tvContent.text = content.content.textFromHtml()
        tvDate.text = content.title.publicationDate.milliseconds.formatToDate()
    }

    override fun showError(msg: String) {
        Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show()
    }


}
