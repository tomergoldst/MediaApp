package com.tomergoldst.mediaapp.data

import com.tomergoldst.mediaapp.models.Entry

class Repository private constructor(private val mediaRemoteService: DataSource) : DataSource {

    companion object {

        @Volatile
        private var INSTANCE: Repository? = null

        fun getInstance(mediaRemoteService: DataSource):
                Repository =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Repository(mediaRemoteService).also { INSTANCE = it }
            }

    }

    override fun getMediaEntries(callback: DataSource.GetMediaEntriesCallback) {
        mediaRemoteService.getMediaEntries(object : DataSource.GetMediaEntriesCallback{
            override fun onEntriesLoaded(entries: List<Entry>) {
                callback.onEntriesLoaded(entries)
            }

            override fun onDataNotAvailable() {
                callback.onDataNotAvailable()
            }
        })
    }
}
