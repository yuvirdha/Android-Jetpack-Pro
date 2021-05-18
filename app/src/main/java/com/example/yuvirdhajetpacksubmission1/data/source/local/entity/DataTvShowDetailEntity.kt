package com.example.yuvirdhajetpacksubmission1.data.source.local.entity

import androidx.room.Embedded
import androidx.room.Relation

data class DataTvShowDetailEntity (
    @Embedded
    var mDataTvShow: DataTvShowEntity,

    @Relation(parentColumn = "title", entityColumn = "title")
    var mDataTvShowDetail: List<DataTvShowEntity>
)