package com.tomergoldst.mediaapp.data

import com.tomergoldst.mediaapp.models.Entry

class Repository(private val mediaRemoteService: DataSource) : DataSource {

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
