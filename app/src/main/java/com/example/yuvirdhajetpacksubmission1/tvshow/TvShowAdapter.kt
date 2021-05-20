package com.example.yuvirdhajetpacksubmission1.tvshow

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.yuvirdhajetpacksubmission1.R
import com.example.yuvirdhajetpacksubmission1.data.source.local.entity.DataTvShowEntity
import com.example.yuvirdhajetpacksubmission1.databinding.ItemListBinding
import com.example.yuvirdhajetpacksubmission1.detail.TvShowDetailActivity

class TvShowAdapter : PagedListAdapter<DataTvShowEntity, TvShowAdapter.TvShowViewHolder>(DIFF_CALLBACK) {

    companion object{
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DataTvShowEntity>(){
            override fun areItemsTheSame(oldItem: DataTvShowEntity, newItem: DataTvShowEntity): Boolean {
                return oldItem.title == newItem.title
            }

            override fun areContentsTheSame(oldItem: DataTvShowEntity, newItem: DataTvShowEntity): Boolean {
                return oldItem.title == newItem.title
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        val binding = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvShowViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        val tvShows = getItem(position)
        if (tvShows != null)
            holder.bind(tvShows)
    }

    class TvShowViewHolder(private val binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(dataMovie: DataTvShowEntity) {
            with(binding) {
                tvTitle.text = dataMovie.title
                tvDetail.text = dataMovie.detail
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, TvShowDetailActivity::class.java)
                    intent.putExtra(TvShowDetailActivity.EXTRA_TV_SHOW, dataMovie.title)
                    itemView.context.startActivity(intent)
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

