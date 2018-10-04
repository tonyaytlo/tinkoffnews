package com.tony.tinkoffnews.data.repository

import com.tony.tinkoffnews.data.entity.NewsItem
import com.tony.tinkoffnews.data.repository.datasource.NewsDataStoreFactory
import com.tony.tinkoffnews.domain.repository.NewsRepository
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsDataRepository
@Inject constructor(private val factory: NewsDataStoreFactory) : NewsRepository {

    override fun fetchNewsList(): Single<List<NewsItem>> = factory.fetchNews().singleOrError()

    override fun getNewsContent(id: Int) = factory.getNewsContent(id).singleOrError()

    override fun getNewsList() = factory.getNews().singleOrError()


}