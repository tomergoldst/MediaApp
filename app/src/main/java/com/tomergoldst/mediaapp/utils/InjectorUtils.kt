package com.tomergoldst.mediaapp.utils

import com.tomergoldst.mediaapp.data.Repository
import com.tomergoldst.mediaapp.data.remote.MediaRemoteDataSource
import com.tomergoldst.mediaapp.data.remote.MediaService
import com.tomergoldst.mediaapp.data.remote.RetrofitClient
import com.tomergoldst.mediaapp.data.remote.RetrofitMediaService
import com.tomergoldst.mediaapp.ui.ViewModelProvider
import retrofit2.Retrofit

// Inject classes needed for various Activities and Fragments.
object InjectorUtils {

    fun getViewModelProvider(): ViewModelProvider {
        return ViewModelProvider(getRepository())
    }

    private fun getRetrofit(): Retrofit {
        return RetrofitClient.retrofit!!
    }

    private fun getMediaService(): MediaService {
        return RetrofitMediaService.getInstance(getRetrofit()).mediaService!!
    }

    private fun getRepository(): Repository {
        return Repository.getInstance(MediaRemoteDataSource(getMediaService()))
    }

}