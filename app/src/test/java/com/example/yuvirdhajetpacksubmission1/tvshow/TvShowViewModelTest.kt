package com.example.yuvirdhajetpacksubmission1.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.paging.PositionalDataSource
import com.example.yuvirdhajetpacksubmission1.data.DataRepository
import com.example.yuvirdhajetpacksubmission1.data.source.local.entity.DataTvShowEntity
import com.example.yuvirdhajetpacksubmission1.utils.MyDummy
import com.example.yuvirdhajetpacksubmission1.vo.Resource
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import java.util.concurrent.Executors

@RunWith(MockitoJUnitRunner::class)
class TvShowViewModelTest{

    private lateinit var viewModel: TvShowViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    @Mock
    private lateinit var repo: DataRepository

    @Mock
    private lateinit var observer: Observer<Resource<PagedList<DataTvShowEntity>>>

//    @Mock
//    private lateinit var pagedList: PagedList<DataTvShowEntity>

    @Before
    fun setUp(){
        viewModel = TvShowViewModel(repo)
    }

    @Test
    fun `getTvShows should be success`() {
        val tvShows = PagedTestDataSources.snapshot(MyDummy.generateDummyTvShows())
        val expected = MutableLiveData<Resource<PagedList<DataTvShowEntity>>>()
        expected.value = Resource.success(tvShows)

        `when`(repo.generateDummyTvShows()).thenReturn(expected)

        viewModel.getTvShowData().observeForever(observer)
        Mockito.verify(observer).onChanged(expected.value)

        val expectedValue = expected.value
        val actualValue = viewModel.getTvShowData().value
        assertEquals(expectedValue, actualValue)
        assertEquals(expectedValue?.data, actualValue?.data)
        assertEquals(expectedValue?.data?.size, actualValue?.data?.size)
    }

    @Test
    fun `getTvShows should be success but data is empty`() {
        val tvShows = PagedTestDataSources.snapshot()
        val expected = MutableLiveData<Resource<PagedList<DataTvShowEntity>>>()
        expected.value = Resource.success(tvShows)

        `when`(repo.generateDummyTvShows()).thenReturn(expected)

        viewModel.getTvShowData().observeForever(observer)
        Mockito.verify(observer).onChanged(expected.value)

        val actualValueDataSize = viewModel.getTvShowData().value?.data?.size
        Assert.assertTrue(
            "size of data should be 0, actual is $actualValueDataSize",
            actualValueDataSize == 0
        )
    }


    @Test
    fun `getTvShows should be error`() {
        val expectedMessage = "Something happen dude!"
        val expected = MutableLiveData<Resource<PagedList<DataTvShowEntity>>>()
        expected.value = Resource.error(expectedMessage, null)

        `when`(repo.generateDummyTvShows()).thenReturn(expected)

        viewModel.getTvShowData().observeForever(observer)
        Mockito.verify(observer).onChanged(expected.value)

        val actualMessage = viewModel.getTvShowData().value?.message
        assertEquals(expectedMessage, actualMessage)
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


//    @Test
//    fun getTvShow() {
//        val dummyTvShows = Resource.success(pagedList)
//        `when`(dummyTvShows.data?.size).thenReturn(10)
//
//        val tvShows = MutableLiveData<Resource<PagedList<DataTvShowEntity>>>()
//        tvShows.value = dummyTvShows
//
//        `when`(repo.generateDummyTvShows()).thenReturn(tvShows)
//        val dataEntity = viewModel.getTvShowData().value?.data
//
//        Mockito.verify(repo).generateDummyTvShows()
//        assertNotNull(dataEntity)
//        assertEquals(10, dataEntity?.size)
//
//        viewModel.getTvShowData().observeForever(observer)
//        Mockito.verify(observer).onChanged(dummyTvShows)
//    }
}