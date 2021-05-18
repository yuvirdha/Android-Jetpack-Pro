package com.example.yuvirdhajetpacksubmission1.data.source.remote

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.yuvirdhajetpacksubmission1.data.source.remote.response.ApiResponse
import com.example.yuvirdhajetpacksubmission1.data.source.remote.response.DataMovieEntityResponse
import com.example.yuvirdhajetpacksubmission1.data.source.remote.response.DataTvShowEntityResponse
import com.example.yuvirdhajetpacksubmission1.utils.EspressoIdlingResource
import com.example.yuvirdhajetpacksubmission1.utils.MyJsonHelper

class RemoteDataSource private constructor(private val myJsonHelper: MyJsonHelper){

    //handler --> atur waktu delay sesuai dengan kebutuhan (data yang diperoleh disimulasikan berasal dari server atau API) --> tidak dianjurkan

    private val myHandler = Handler(Looper.getMainLooper())
    companion object {
        private const val SERVICE_LATENCY_IN_MILLIS: Long = 2000

        @Volatile
        private var instance: RemoteDataSource? = null

        //buat kelas RemoteDataSource sebagai Singleton --> tercipta satu instance saja di dalam JVM

        fun getInstance(helper: MyJsonHelper): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(helper).apply { instance = this }
            }
    }

    fun getAllMovies(): LiveData<ApiResponse<List<DataMovieEntityResponse>>> {
        EspressoIdlingResource.increment()
        val resultMovies = MutableLiveData<ApiResponse<List<DataMovieEntityResponse>>>()
        myHandler.postDelayed({
            resultMovies.value = ApiResponse.success(myJsonHelper.loadMovies())
            EspressoIdlingResource.decrement()}, SERVICE_LATENCY_IN_MILLIS)
        return resultMovies
    }

    fun getAllTvShows(): LiveData<ApiResponse<List<DataTvShowEntityResponse>>> {
        EspressoIdlingResource.increment()
        val resultTvShows = MutableLiveData<ApiResponse<List<DataTvShowEntityResponse>>>()
        myHandler.postDelayed({
            resultTvShows.value = ApiResponse.success(myJsonHelper.loadTvShows())
            EspressoIdlingResource.decrement()}, SERVICE_LATENCY_IN_MILLIS)
        return resultTvShows
    }
}