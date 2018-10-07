package com.tony.tinkoffnews.presentation.internal.di.modules

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.tony.tinkoffnews.NewsApp
import com.tony.tinkoffnews.data.cache.NewsCache
import com.tony.tinkoffnews.data.executor.JobExecutor
import com.tony.tinkoffnews.data.repository.NewsDataRepository
import com.tony.tinkoffnews.data.repository.datasource.NewsCacheStore
import com.tony.tinkoffnews.domain.repository.NewsRepository
import com.tony.tinkoffnews.presentation.UiThread
import dagger.Module
import dagger.Provides
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import org.buffer.android.boilerplate.domain.executor.PostExecutionThread
import org.buffer.android.boilerplate.domain.executor.ThreadExecutor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.galt.app.domain.api.NewsService
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


private const val WRITE_TIMEOUT = 10L
private const val READ_TIMEOUT = 10L
private const val BASE_URL = " https://api.tinkoff.ru/v1/"
private const val PREFERENCES_NAME = "ru.tinkoffnews.app"


@Module
class ApplicationModule constructor(private val application: NewsApp) {


    @Provides
    @Singleton
    internal fun provideApplicationContext(): Context {
        return this.application
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder()
                .create()
    }

    @Provides
    @Singleton
    fun provideNewsService(retrofit: Retrofit): NewsService {
        return retrofit.create(NewsService::class.java)
    }

    @Provides
    @Singleton
    fun provideBaseUrl(): HttpUrl? = HttpUrl.parse(BASE_URL)

    @Provides
    @Singleton
    fun provideRetrofitBuilder(): Retrofit.Builder = Retrofit.Builder()

    @Provides
    @Singleton
    fun provideOkHttp(): OkHttpClient {
        return OkHttpClient.Builder()
                .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .build()
    }

    @Provides
    @Singleton
    fun provideGsonConverter(gson: Gson): GsonConverterFactory = GsonConverterFactory.create(gson)

    @Provides
    @Singleton
    fun provideSharedPreferences(context: Context): SharedPreferences =
            context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)

    @Provides
    @Singleton
    fun provideAdapter() = RxJava2CallAdapterFactory.create()

    @Provides
    @Singleton
    fun provideRetrofit(builder: Retrofit.Builder,
                        okHttpClient: OkHttpClient,
                        gsonConverterFactory: GsonConverterFactory,
                        rxJava2CallAdapterFactory: RxJava2CallAdapterFactory,
                        url: HttpUrl?): Retrofit {
        return builder
                .addCallAdapterFactory(rxJava2CallAdapterFactory)
                .addConverterFactory(gsonConverterFactory)
                .client(okHttpClient)
                .baseUrl(url)
                .build()
    }


    @Provides
    @Singleton
    fun provideThreadExecutor(jobExecutor: JobExecutor): ThreadExecutor {
        return jobExecutor
    }

    @Provides
    @Singleton
    fun providePostExecutionThread(uiThread: UiThread): PostExecutionThread {
        return uiThread
    }


    @Provides
    @Singleton
    fun provideUserCache(userCache: NewsCacheStore): NewsCache {
        return userCache
    }

    @Provides
    @Singleton
    fun provideNewsRepository(userDataRepository: NewsDataRepository): NewsRepository {
        return userDataRepository
    }

}
