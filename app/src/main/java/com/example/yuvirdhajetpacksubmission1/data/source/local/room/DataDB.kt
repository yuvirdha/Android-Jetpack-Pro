package com.example.yuvirdhajetpacksubmission1.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.yuvirdhajetpacksubmission1.data.source.local.entity.DataMovieEntity
import com.example.yuvirdhajetpacksubmission1.data.source.local.entity.DataTvShowEntity

@Database(entities = [DataMovieEntity::class, DataTvShowEntity::class],
    version = 1,
    exportSchema = false)
abstract class DataDB : RoomDatabase() {
    abstract fun dataDao(): DataDao

    companion object {

        @Volatile
        private var INSTANCE: DataDB? = null

        fun getInstance(context: Context): DataDB =
            INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    DataDB::class.java,
                    "myCatalogue.db"
                ).build().apply {
                    INSTANCE = this
                }
            }
    }
}