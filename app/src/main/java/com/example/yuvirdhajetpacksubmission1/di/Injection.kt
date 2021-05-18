package com.example.yuvirdhajetpacksubmission1.di

import android.content.Context
import com.example.yuvirdhajetpacksubmission1.data.DataRepository
import com.example.yuvirdhajetpacksubmission1.data.source.local.LocalDataSource
import com.example.yuvirdhajetpacksubmission1.data.source.local.room.DataDB
import com.example.yuvirdhajetpacksubmission1.data.source.remote.RemoteDataSource
import com.example.yuvirdhajetpacksubmission1.utils.AppExecutors
import com.example.yuvirdhajetpacksubmission1.utils.MyJsonHelper

object Injection {
    fun provideRepository(context: Context): DataRepository {

        val database = DataDB.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance(MyJsonHelper(context))
        val localDataSource = LocalDataSource.getInstance(database.dataDao())
        val appExecutors = AppExecutors()


        return DataRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}