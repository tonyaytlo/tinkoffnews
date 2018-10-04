package com.tony.tinkoffnews.data.repository.datasource

import com.tony.tinkoffnews.data.entity.NewsContent
import com.tony.tinkoffnews.data.entity.NewsItem
import io.reactivex.Observable

interface NewsDataStore {

    fun getNewsList(): Observable<List<NewsItem>>

    fun getNewsContent(id: Int): Observable<NewsContent>
}