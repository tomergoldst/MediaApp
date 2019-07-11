package com.tomergoldst.mediaapp.data

import com.tomergoldst.mediaapp.models.Entry

interface DataSource {

    interface GetMediaEntriesCallback {
        fun onEntriesLoaded(entries: List<Entry>)
        fun onDataNotAvailable()
    }

    fun getMediaEntries(callback: GetMediaEntriesCallback)

}
