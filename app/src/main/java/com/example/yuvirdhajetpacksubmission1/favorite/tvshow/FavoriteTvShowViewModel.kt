package com.example.yuvirdhajetpacksubmission1.favorite.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.yuvirdhajetpacksubmission1.data.DataRepository
import com.example.yuvirdhajetpacksubmission1.data.source.local.entity.DataTvShowEntity

class FavoriteTvShowViewModel(private val repo: DataRepository) : ViewModel() {
    fun getFavTvShow(): LiveData<PagedList<DataTvShowEntity>> = repo.getFavoriteTvShow()

    fun setTvShowFavorited(tvShow: DataTvShowEntity) {
        val newState = !tvShow.isFav
        repo.setTvShowIsFav(tvShow, newState)
    }
}