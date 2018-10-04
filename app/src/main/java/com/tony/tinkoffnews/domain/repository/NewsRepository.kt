package com.tony.tinkoffnews.domain.repository

import com.tony.tinkoffnews.data.entity.NewsContent
import com.tony.tinkoffnews.data.entity.NewsItem
import io.reactivex.Single

interface NewsRepository {

    fun getNewsList(): Single<List<NewsItem>>

    fun fetchNewsList(): Single<List<NewsItem>>

    fun getNewsContent(id: Int): Single<NewsContent>


}