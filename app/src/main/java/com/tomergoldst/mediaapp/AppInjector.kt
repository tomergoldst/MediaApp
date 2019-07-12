package com.tomergoldst.mediaapp

import com.tomergoldst.mediaapp.data.DataSource
import com.tomergoldst.mediaapp.data.Repository
import com.tomergoldst.mediaapp.data.remote.MediaRemoteDataSource
import com.tomergoldst.mediaapp.data.remote.MediaService
import com.tomergoldst.mediaapp.data.remote.RetrofitClient
import com.tomergoldst.mediaapp.ui.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit

private val retrofit: Retrofit = RetrofitClient.retrofit!!
private val mediaService: MediaService = retrofit.create(MediaService::class.java)

val appModules: Module = module {
    single { mediaService }
    single<DataSource>(named("MediaRemoteService")) { MediaRemoteDataSource( mediaService = get()) }
    single(named("Repository")) { Repository(mediaRemoteService = get(named("MediaRemoteService"))) }
    viewModel { MainViewModel(repository = get(named("Repository"))) }
}

