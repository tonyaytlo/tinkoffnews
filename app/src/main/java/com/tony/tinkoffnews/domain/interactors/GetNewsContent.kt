package com.tony.tinkoffnews.domain.interactors

import com.tony.tinkoffnews.data.entity.NewsContent
import com.tony.tinkoffnews.domain.repository.NewsRepository
import io.reactivex.Single
import org.buffer.android.boilerplate.domain.executor.PostExecutionThread
import org.buffer.android.boilerplate.domain.executor.ThreadExecutor
import javax.inject.Inject

class GetNewsContent
@Inject constructor(private val newsRepository: NewsRepository,
                    threadExecutor: ThreadExecutor,
                    postExecutionThread: PostExecutionThread) :
        SingleUseCase<NewsContent, GetNewsContent.Params?>(threadExecutor, postExecutionThread) {

    public override fun buildUseCaseObservable(params: Params?): Single<NewsContent> =
            newsRepository.getNewsContent(params?.id ?: "")


    data class Params(var id: String)
}