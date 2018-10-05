package com.tony.tinkoffnews.domain.interactors

import com.tony.tinkoffnews.data.entity.NewsItem
import com.tony.tinkoffnews.domain.repository.NewsRepository
import org.buffer.android.boilerplate.domain.executor.PostExecutionThread
import org.buffer.android.boilerplate.domain.executor.ThreadExecutor
import javax.inject.Inject

class GetNewsList
@Inject constructor(private val newsRepository: NewsRepository,
                    threadExecutor: ThreadExecutor,
                    postExecutionThread: PostExecutionThread) :
        SingleUseCase<List<NewsItem>, Void?>(threadExecutor, postExecutionThread) {

    public override fun buildUseCaseObservable(params: Void?) = newsRepository.getNewsList()
}