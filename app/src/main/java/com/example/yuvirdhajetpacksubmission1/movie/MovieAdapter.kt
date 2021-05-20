package com.example.yuvirdhajetpacksubmission1.movie

import android.content.ContentValues.TAG
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.yuvirdhajetpacksubmission1.R
import com.example.yuvirdhajetpacksubmission1.data.source.local.entity.DataMovieEntity
import com.example.yuvirdhajetpacksubmission1.databinding.ItemListBinding
import com.example.yuvirdhajetpacksubmission1.detail.MovieDetailActivity

class MovieAdapter : PagedListAdapter<DataMovieEntity, MovieAdapter.MovieViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DataMovieEntity>() {
            override fun areItemsTheSame(oldItem: DataMovieEntity, newItem: DataMovieEntity): Boolean {
                return oldItem.title == newItem.title
            }

            override fun areContentsTheSame(oldItem: DataMovieEntity, newItem: DataMovieEntity): Boolean {
                return oldItem.title == newItem.title
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val movieBinding = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(movieBinding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movies = getItem(position)
        if (movies != null)
            holder.bind(movies)
        Log.d(TAG, "test binding: " + getItem(position))
    }

    class MovieViewHolder(private val binding: ItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(dataMovies: DataMovieEntity) {
            with(binding) {
                Log.d(TAG, "Coba fun bind")
                tvTitle.text = dataMovies.title
                tvDetail.text = dataMovies.detail
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, MovieDetailActivity::class.java).apply {
                        putExtra(MovieDetailActivity.EXTRA_TITLE, dataMovies.title)
                    }
                    it.context.startActivity(intent)
                }
                Glide.with(itemView.context)
                    .load(dataMovies.poster)
                    .apply(RequestOptions().override(120, 150))
                    .into(myposter)
            }
        }
    }
}