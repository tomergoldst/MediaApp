package com.tomergoldst.mediaapp

import com.tomergoldst.mediaapp.data.DataSource
import com.tomergoldst.mediaapp.data.Repository
import com.tomergoldst.mediaapp.data.remote.MediaRemoteDataSource
import com.tomergoldst.mediaapp.data.remote.MediaService
import com.tomergoldst.mediaapp.data.remote.RetrofitClient
import com.tomergoldst.mediaapp.ui.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit

private val retrofit: Retrofit = RetrofitClient.retrofit!!
private val mediaService: MediaService = retrofit.create(MediaService::class.java)

val appModule: Module = module {
    single { mediaService }
    single { MediaRemoteDataSource( mediaService = get()) }
    single { Repository(mediaRemoteService = get() as DataSource) }
    viewModel { MainViewModel(repository = get() as DataSource) }
}

