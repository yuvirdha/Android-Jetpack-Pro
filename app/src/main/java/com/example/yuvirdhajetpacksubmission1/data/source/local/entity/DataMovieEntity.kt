package com.example.yuvirdhajetpacksubmission1.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "data_entities_movies")
data class DataMovieEntity(

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "year")
    var year: String,

    @ColumnInfo(name = "genre")
    var genre: String,

    @ColumnInfo(name = "detail")
    var detail: String,

    @ColumnInfo(name = "poster")
    var poster: String,

    @ColumnInfo(name = "isFav")
    var isFav: Boolean = false
)