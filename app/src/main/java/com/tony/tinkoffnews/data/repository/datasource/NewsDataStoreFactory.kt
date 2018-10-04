package com.tony.tinkoffnews.data.repository.datasource

import com.tony.tinkoffnews.data.cache.NewsCache
import com.tony.tinkoffnews.data.entity.NewsItem
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsDataStoreFactory
@Inject constructor(val newsCache: NewsCache, val remoteService: RemoteNewsStore) {

    fun getNews(): Observable<List<NewsItem>> {
        if (newsCache.isCached()) {
            return newsCache.get()
        }
        return fetchNews()
    }

    fun fetchNews() = remoteService.getNewsList() // INJECT CYCLER

    fun getNewsContent(id: Int) = remoteService.getNewsContent(id)

}