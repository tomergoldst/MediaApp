package com.tomergoldst.mediaapp.data.remote

import retrofit2.Retrofit

class RetrofitMediaService private constructor(retrofit: Retrofit) {

    var mediaService: MediaService? = null
        private set(value) {
            field = value
        }

    init {
        mediaService = retrofit.create(MediaService::class.java)
    }

    companion object {
        @Volatile
        private var INSTANCE: RetrofitMediaService? = null

        fun getInstance(retrofit: Retrofit):
                RetrofitMediaService =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: RetrofitMediaService(retrofit).also { INSTANCE = it }
            }

    }

}