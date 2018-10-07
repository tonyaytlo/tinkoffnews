package com.tony.tinkoffnews.domain.interactors

import com.tony.tinkoffnews.data.entity.NewsItem
import com.tony.tinkoffnews.domain.repository.NewsRepository
import io.reactivex.Single
import org.buffer.android.boilerplate.domain.executor.PostExecutionThread
import org.buffer.android.boilerplate.domain.executor.ThreadExecutor
import javax.inject.Inject

class GetNewsList
@Inject constructor(private val newsRepository: NewsRepository,
                    threadExecutor: ThreadExecutor,
                    postExecutionThread: PostExecutionThread) :
        SingleUseCase<List<NewsItem>, GetNewsList.Params?>(threadExecutor, postExecutionThread) {

    public override fun buildUseCaseObservable(params: Params?): Single<List<NewsItem>> {
        if (params?.fetch == true) {
            return newsRepository.fetchNewsList()
        }
        return newsRepository.getNewsList()
    }


    class Params private constructor(val fetch: Boolean) {
        companion object {
            fun fetch(fetch: Boolean): Params {
                return Params(fetch)
            }
        }
    }
}