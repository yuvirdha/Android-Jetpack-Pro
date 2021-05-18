package com.example.yuvirdhajetpacksubmission1.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.yuvirdhajetpacksubmission1.data.DataRepository
import com.example.yuvirdhajetpacksubmission1.data.source.local.entity.DataTvShowEntity
import com.example.yuvirdhajetpacksubmission1.vo.Resource

class TvShowViewModel(private val repository: DataRepository) : ViewModel() {
    fun getTvShowData(): LiveData<Resource<PagedList<DataTvShowEntity>>> = repository.generateDummyTvShows()
}