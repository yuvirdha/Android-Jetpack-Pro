package com.example.yuvirdhajetpacksubmission1.movie

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.yuvirdhajetpacksubmission1.databinding.FragmentMovieBinding
import com.example.yuvirdhajetpacksubmission1.viewmodel.ViewModelFactory
import com.example.yuvirdhajetpacksubmission1.vo.Status

class MovieFragment : Fragment() {

    private var _binding: FragmentMovieBinding? = null
    private val movieBinding get() = _binding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): ConstraintLayout? {

        _binding = FragmentMovieBinding.inflate(layoutInflater, container, false)
        return movieBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {

            //set viewmodel
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[MovieViewModel::class.java]

            val movieAdapter = MovieAdapter()

            viewModel.getMovieData().observe(this, { movies ->
                if (movies != null) {
                    when (movies.status) {
                        Status.LOADING -> movieBinding?.progressBar?.visibility = View.VISIBLE
                        Status.SUCCESS -> {
                            movieBinding?.progressBar?.visibility = View.GONE
                            movieAdapter.submitList(movies.data)
                            Log.d(TAG,"test: " + movies.data)
                        }
                        Status.ERROR -> {
                            movieBinding?.progressBar?.visibility = View.GONE
                            Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
                Log.d(TAG,"entered here: " + movies.data)
            })


            with(movieBinding?.rvMovie) {
                this?.layoutManager = LinearLayoutManager(context)
                //this?.setHasFixedSize(true)
                this?.adapter = movieAdapter
                Log.d(TAG, "recyclerView: $movieAdapter")
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
