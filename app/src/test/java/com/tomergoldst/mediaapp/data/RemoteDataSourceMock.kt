package com.tomergoldst.mediaapp.data

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import com.tomergoldst.mediaapp.models.MediaResponse
import java.io.FileReader

class RemoteDataSourceMock : DataSource {

    override fun getMediaEntries(callback: DataSource.GetMediaEntriesCallback) {
        try {

            // todo: create a util class to read json from file
            val response = object : TypeToken<MediaResponse>() {
            }.type
            val gson = Gson()
            val classLoader = ClassLoader.getSystemClassLoader()
            val url = classLoader.getResource("link_entries.json")
            val file = url.file
            val reader = JsonReader(FileReader(file))
            val data = gson.fromJson<MediaResponse>(reader, response)

            callback.onEntriesLoaded(data.entry)

        } catch (e: Exception) {
            callback.onDataNotAvailable()
        }

    }

}