package com.example.yuvirdhajetpacksubmission1.data.source.remote.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DataMovieEntityResponse(
    var title: String,
    var year: String,
    var genre: String,
    var detail: String,
    var poster: String
): Parcelable