package com.example.yuvirdhajetpacksubmission1.favorite.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.paging.PositionalDataSource
import com.example.yuvirdhajetpacksubmission1.data.DataRepository
import com.example.yuvirdhajetpacksubmission1.data.source.local.entity.DataTvShowEntity
import com.example.yuvirdhajetpacksubmission1.utils.MyDummy
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import java.util.concurrent.Executors

@RunWith(MockitoJUnitRunner::class)
class FavoriteTvShowViewModelTest {
    private lateinit var viewModel: FavoriteTvShowViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repo: DataRepository

    @Mock
    private lateinit var observer: Observer<PagedList<DataTvShowEntity>>

    @Before
    fun setUp() {
        viewModel = FavoriteTvShowViewModel(repo)
    }
    @Test
    fun `getFavTvShow should be success`() {
        val expected = MutableLiveData<PagedList<DataTvShowEntity>>()
        expected.value = PagedTestDataSources.snapshot(MyDummy.generateDummyTvShows())

        Mockito.`when`(repo.getFavoriteTvShow()).thenReturn(expected)

        viewModel.getFavTvShow().observeForever(observer)
        Mockito.verify(observer).onChanged(expected.value)

        val expectedValue = expected.value
        val actualValue = viewModel.getFavTvShow().value
        Assert.assertEquals(expectedValue, actualValue)
        Assert.assertEquals(expectedValue?.snapshot(), actualValue?.snapshot())
        Assert.assertEquals(expectedValue?.size, actualValue?.size)
    }

    @Test
    fun `getFavTvShow should be success but data is empty`() {
        val expected = MutableLiveData<PagedList<DataTvShowEntity>>()
        expected.value = PagedTestDataSources.snapshot()

        Mockito.`when`(repo.getFavoriteTvShow()).thenReturn(expected)

        viewModel.getFavTvShow().observeForever(observer)
        Mockito.verify(observer).onChanged(expected.value)

        val actualValueDataSize = viewModel.getFavTvShow().value?.size
        Assert.assertTrue("size of data should be 0, actual is $actualValueDataSize", actualValueDataSize == 0)
    }

    class PagedTestDataSources private constructor(private val items: List<DataTvShowEntity>) : PositionalDataSource<DataTvShowEntity>() {
        companion object {
            fun snapshot(items: List<DataTvShowEntity> = listOf()): PagedList<DataTvShowEntity> {
                return PagedList.Builder(PagedTestDataSources(items), 10)
                    .setNotifyExecutor(Executors.newSingleThreadExecutor())
                    .setFetchExecutor(Executors.newSingleThreadExecutor())
                    .build()
            }
        }

        override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<DataTvShowEntity>) {
            callback.onResult(items, 0, items.size)
        }

        override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<DataTvShowEntity>) {
            val start = params.startPosition
            val end = params.startPosition + params.loadSize
            callback.onResult(items.subList(start, end))
        }
    }

    @Test
    fun setTvShowFavorited(){
        val dummyFavTvShow = MyDummy.generateDummyTvShows()[0]
        viewModel.setTvShowFavorited(dummyFavTvShow)
        Mockito.verify(repo).setTvShowIsFav(dummyFavTvShow, !dummyFavTvShow.isFav)
    }

}