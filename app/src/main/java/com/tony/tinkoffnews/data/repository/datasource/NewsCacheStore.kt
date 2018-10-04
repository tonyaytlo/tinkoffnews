package com.tony.tinkoffnews.data.repository.datasource

import android.content.Context
import com.tony.tinkoffnews.data.cache.FileManager
import com.tony.tinkoffnews.data.cache.NewsCache
import com.tony.tinkoffnews.data.cache.serializer.Serializer
import com.tony.tinkoffnews.data.entity.NewsItem
import io.reactivex.Observable
import org.buffer.android.boilerplate.domain.executor.ThreadExecutor
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class NewsCacheStore
@Inject constructor(context: Context,
                    private val serializer: Serializer,
                    private val fileManager: FileManager,
                    private val executor: ThreadExecutor) : NewsCache {


    private val DEFAULT_FILE_NAME = "db_file"

    private val cacheDir: File = context.cacheDir
    private val dbFile: File by lazy { buildFile() }


    override fun isCached() = fileManager.exists(buildFile())

    override fun put(data: List<NewsItem>) {
        if (isCached()) {
            clear()
        }
        val jsonString = this.serializer.serialize(data)
        executeAsynchronously(CacheWriter(this.fileManager, dbFile, jsonString))
    }

    override fun get(): Observable<List<NewsItem>> =
            Observable.create<List<NewsItem>> { emitter ->
                val fileContent = fileManager.readFileContent(dbFile)
                val newsEntity = serializer.deserializeList<NewsItem>(fileContent)
                if (newsEntity != null) {
                    emitter.onNext(newsEntity)
                    emitter.onComplete()
                } else {
                    emitter.onError(IllegalArgumentException())
                }
            }

    override fun clear() {
        fileManager.clearDirectory(dbFile)
    }

    private fun buildFile(): File {
        val fileNameBuilder = StringBuilder()
        fileNameBuilder.append(this.cacheDir.path)
        fileNameBuilder.append(File.separator)
        fileNameBuilder.append(DEFAULT_FILE_NAME)

        return File(fileNameBuilder.toString())
    }

    private fun executeAsynchronously(runnable: Runnable) {
        executor.execute(runnable)
    }

    private class CacheWriter
    internal constructor(private val fileManager: FileManager, private val fileToWrite: File,
                         private val fileContent: String) : Runnable {

        override fun run() {
            this.fileManager.writeToFile(fileToWrite, fileContent)
        }
    }
}