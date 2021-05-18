package com.example.yuvirdhajetpacksubmission1.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.yuvirdhajetpacksubmission1.data.source.local.entity.DataMovieEntity
import com.example.yuvirdhajetpacksubmission1.data.DataRepository
import com.example.yuvirdhajetpacksubmission1.vo.Resource

class MovieViewModel(private val repository: DataRepository): ViewModel() {
    fun getMovieData(): LiveData<Resource<PagedList<DataMovieEntity>>> = repository.generateDummyMovies()
}