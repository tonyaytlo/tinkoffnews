package com.tony.tinkoffnews.data.repository.datasource

import com.tony.tinkoffnews.data.cache.NewsCache
import com.tony.tinkoffnews.data.entity.NewsContent
import com.tony.tinkoffnews.data.entity.NewsItem
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import ru.galt.app.domain.api.NewsService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteNewsStore
@Inject constructor(private val service: NewsService,
                    private val cacheStore: NewsCache) : NewsDataStore {

    override fun getNewsList(): Observable<List<NewsItem>> =
            service.getNewsEntityList()
                    .observeOn(Schedulers.computation())
                    .map {
                        it.payload.sortedByDescending {
                            it.publicationDate.milliseconds // SORT BY
                        }
                    }

                    .doOnNext {
                        cacheStore.put(it)
                    }

    override fun getNewsContent(id: String): Observable<NewsContent> {
        return service.getNewsContentEntity(id)
                .map {
                    it.payload
                }
    }
}