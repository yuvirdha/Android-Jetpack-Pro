package com.example.yuvirdhajetpacksubmission1.utils

import android.content.Context
import com.example.yuvirdhajetpacksubmission1.data.source.remote.response.DataMovieEntityResponse
import com.example.yuvirdhajetpacksubmission1.data.source.remote.response.DataTvShowEntityResponse
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

class MyJsonHelper(private val context: Context) {

    private fun parsingFileToString(fileName: String): String? {
        return try {
            val `is` = context.assets.open(fileName)
            val buffer = ByteArray(`is`.available())
            `is`.read(buffer)
            `is`.close()
            String(buffer)

        } catch (ex: IOException) {
            ex.printStackTrace()
            null
        }
    }

    fun loadMovies(): List<DataMovieEntityResponse> {
        val list = ArrayList<DataMovieEntityResponse>()
        try {
            val responseObject = JSONObject(parsingFileToString("MoviesResponses.json").toString())
            val listArray = responseObject.getJSONArray("movie")
            for (i in 0 until listArray.length()) {
                val data = listArray.getJSONObject(i)

                val title = data.getString("title")
                val year = data.getString("year")
                val genre = data.getString("genre")
                val detail = data.getString("detail")
                val poster = data.getString("poster")

                val courseResponse = DataMovieEntityResponse(title, year, genre, detail, poster)
                list.add(courseResponse)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return list
    }

    fun loadTvShows(): List<DataTvShowEntityResponse> {
        val list = ArrayList<DataTvShowEntityResponse>()
        try {
            val responseObject = JSONObject(parsingFileToString("TvShowsResponses.json").toString())
            val listArray = responseObject.getJSONArray("tvShows")
            for (i in 0 until listArray.length()) {
                val data = listArray.getJSONObject(i)

                val title = data.getString("title")
                val year = data.getString("year")
                val genre = data.getString("genre")
                val detail = data.getString("detail")
                val poster = data.getString("poster")

                val courseResponse = DataTvShowEntityResponse(title, year, genre, detail, poster)
                list.add(courseResponse)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return list
    }
}