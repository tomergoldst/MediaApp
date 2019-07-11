package com.tomergoldst.mediaapp.data.remote

import com.tomergoldst.mediaapp.data.DataSource
import com.tomergoldst.mediaapp.models.Entry
import com.tomergoldst.mediaapp.models.MediaResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class MediaRemoteDataSource(
    private val mediaService: MediaService
) :
    DataSource {

    override fun getMediaEntries(callback: DataSource.GetMediaEntriesCallback) {
        val entries: MutableList<Entry> = ArrayList()

        mediaService.getLinkEntries().enqueue(object : Callback<MediaResponse> {
            override fun onFailure(call: Call<MediaResponse>, t: Throwable) {
                Timber.e(t, "onFailure")
            }

            override fun onResponse(
                call: Call<MediaResponse>,
                response: Response<MediaResponse>
            ) {

                entries.addAll(response.body()!!.entry)

                mediaService.getVideoEntries().enqueue(object : Callback<MediaResponse> {
                    override fun onFailure(call: Call<MediaResponse>, t: Throwable) {
                        Timber.e(t, "onFailure")
                    }

                    override fun onResponse(
                        call: Call<MediaResponse>,
                        response: Response<MediaResponse>
                    ) {
                        entries.addAll(response.body()!!.entry)
                    }
                })

                callback.onEntriesLoaded(entries)
            }
        })

    }
}