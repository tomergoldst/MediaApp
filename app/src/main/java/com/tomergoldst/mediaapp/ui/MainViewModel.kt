package com.tomergoldst.mediaapp.ui

import androidx.lifecycle.*
import com.tomergoldst.mediaapp.data.DataSource
import com.tomergoldst.mediaapp.models.Entry

class MainViewModel(
    private val repository: DataSource
) :
    ViewModel() {

    private var _entries = MutableLiveData<List<Entry>>()
    val entries: LiveData<List<Entry>>
        get() = _entries

    private var rawEntries: List<Entry> = emptyList()

    var selectedEntry: Entry? = null

    // Event which triggers the move to link
    private val _eventOpenLink = MutableLiveData<Boolean>()
    val eventOpenLink: LiveData<Boolean>
        get() = _eventOpenLink

    // Event which triggers playing video
    private val _eventPlayVideo = MutableLiveData<Boolean>()
    val eventPlayVideo: LiveData<Boolean>
        get() = _eventPlayVideo

    var queryChanged = false

    init {
        _eventOpenLink.value = false
        _eventPlayVideo.value = false

        repository.getMediaEntries(object : DataSource.GetMediaEntriesCallback {
            override fun onEntriesLoaded(entries: List<Entry>) {
                rawEntries = entries
                _entries.value = entries
            }

            override fun onDataNotAvailable() {

            }
        })
    }

    fun onLinkClicked(entry: Entry) {
        selectedEntry = entry
        _eventOpenLink.value = true
    }

    fun onLinkClickedCompleted() {
        _eventOpenLink.value = false
        selectedEntry = null
    }

    fun onVideoClicked(entry: Entry) {
        selectedEntry = entry
        _eventPlayVideo.value = true
    }

    fun onVideoClickedCompleted() {
        _eventPlayVideo.value = false
        selectedEntry = null
    }

    fun onQueryTextChange(newText: String?) {
        if (newText == null) {
            _entries.value = rawEntries
        } else {
            _entries.value = rawEntries.filter { it.title.contains(newText) }
        }
    }

}