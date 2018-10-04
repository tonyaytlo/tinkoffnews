package com.tony.tinkoffnews.data.repository.datasource

import com.tony.tinkoffnews.data.cache.NewsCache
import com.tony.tinkoffnews.data.entity.NewsContent
import com.tony.tinkoffnews.data.entity.NewsItem
import io.reactivex.Observable
import ru.galt.app.domain.api.NewsService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteNewsStore
@Inject constructor(val service: NewsService, val cacheStore: NewsCache) : NewsDataStore {

    override fun getNewsList(): Observable<List<NewsItem>> =
            service.getNewsEntityList().doOnNext { cacheStore.put(it) }


    override fun getNewsContent(id: Int): Observable<NewsContent> {
        return service.getNewsContentEntity(id)
    }
}