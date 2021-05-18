package com.example.yuvirdhajetpacksubmission1.data.source.local.entity

import androidx.room.Embedded
import androidx.room.Relation

data class DataMovieDetailEntity (
    @Embedded
    var mDataMovie: DataMovieEntity,

    @Relation(parentColumn = "title", entityColumn = "title")
    var mDataMovieDetail: List<DataMovieEntity>
)