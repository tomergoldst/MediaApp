package com.tomergoldst.mediaapp.data.remote

import com.tomergoldst.mediaapp.models.MediaResponse
import retrofit2.Call
import retrofit2.http.GET

interface MediaService {

    @GET("link_json.json")
    fun getLinkEntries(): Call<MediaResponse>

    @GET("video_json.json")
    fun getVideoEntries(): Call<MediaResponse>

}