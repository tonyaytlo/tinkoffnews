package com.tony.tinkoffnews.data.cache.serializer

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Serializer @Inject constructor() {

    private val gson = Gson()

    fun serialize(`object`: Any): String = gson.toJson(`object`)


    fun <T> deserialize(string: String, clazz: Class<T>): T? = gson.fromJson(string, clazz)


    fun <T> deserializeList(string: String): List<T>? {
        val type = object : TypeToken<T>() {}.type
        return gson.fromJson(string, type)
    }
}

