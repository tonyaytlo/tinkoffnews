package com.tony.tinkoffnews.data.cache

import com.tony.tinkoffnews.data.entity.NewsItem
import io.reactivex.Observable

interface NewsCache {

    fun isCached(): Boolean

    fun put(data: List<NewsItem>)

    fun get(): Observable<List<NewsItem>>

    fun clear()
}