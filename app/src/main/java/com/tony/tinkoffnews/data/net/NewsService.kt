package ru.galt.app.domain.api

import com.tony.tinkoffnews.data.entity.NewsContent
import com.tony.tinkoffnews.data.entity.NewsItem
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query


interface NewsService {

    @GET("news")
    fun getNewsEntityList(): Observable<List<NewsItem>>

    @GET("news_content")
    fun getNewsContentEntity(@Query("id") id: Int): Observable<NewsContent>

}