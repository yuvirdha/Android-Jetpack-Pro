package com.example.yuvirdhajetpacksubmission1.favorite.movie

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

class FavoriteMovieAdapter: PagedListAdapter<DataMovieEntity, FavoriteMovieAdapter.FavoriteMovieViewHolder>(DIFF_CALLBACK) {

    companion object{
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DataMovieEntity>(){
            override fun areItemsTheSame(oldItem: DataMovieEntity, newItem: DataMovieEntity): Boolean {
                return oldItem.title == newItem.title
            }

            override fun areContentsTheSame(oldItem: DataMovieEntity, newItem: DataMovieEntity): Boolean {
                return oldItem.title == newItem.title
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteMovieViewHolder {
        val myBinding = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteMovieViewHolder(myBinding)
    }

    override fun onBindViewHolder(holder: FavoriteMovieViewHolder, position: Int) {
        val movies = getItem(position)
        if (movies != null)
            holder.bind(movies)
    }

    fun getSwipedData(swipedPosition: Int): DataMovieEntity? = getItem(swipedPosition)

    class FavoriteMovieViewHolder(private val binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(dataMovie: DataMovieEntity) {
            with(binding) {
                tvTitle.text = dataMovie.title
                tvDetail.text = dataMovie.detail
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, MovieDetailActivity::class.java)
                    intent.putExtra(MovieDetailActivity.EXTRA_TITLE, dataMovie.title)
                    itemView.context.startActivity(intent)
                }
                Glide.with(itemView.context)
                    .load(dataMovie.poster)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error))
                    .into(myposter)
            }
        }
    }
}