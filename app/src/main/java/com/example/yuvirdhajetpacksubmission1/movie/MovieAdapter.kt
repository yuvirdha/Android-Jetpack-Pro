package com.example.yuvirdhajetpacksubmission1.movie

import android.content.Intent
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
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = getItem(position)
        if (movie != null) {
            holder.bind(movie)
        }
    }

    class MovieViewHolder(private val binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(dataMovie: DataMovieEntity) {
            with(binding) {
                tvTitle.text = dataMovie.title
                tvDetail.text = dataMovie.detail
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, MovieDetailActivity::class.java).apply {
                        putExtra(MovieDetailActivity.EXTRA_TITLE, dataMovie.title)
                    }
                    it.context.startActivity(intent)
                }
                Glide.with(itemView.context)
                        .load(dataMovie.poster)
                        .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                                .error(R.drawable.ic_error))
                        .into(myposter)
            }
        }
    }
}
