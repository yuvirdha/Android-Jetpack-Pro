package com.example.yuvirdhajetpacksubmission1.favorite.tvshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.yuvirdhajetpacksubmission1.R
import com.example.yuvirdhajetpacksubmission1.databinding.FragmentFavoriteTvShowBinding
import com.example.yuvirdhajetpacksubmission1.viewmodel.ViewModelFactory
import com.google.android.material.snackbar.Snackbar

class FavoriteTvShowFragment : Fragment() {
    private var _binding: FragmentFavoriteTvShowBinding? = null
    private val binding get() = _binding

    private lateinit var viewModel: FavoriteTvShowViewModel
    private lateinit var adapter: FavoriteTvShowAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFavoriteTvShowBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        itemTouchHelper.attachToRecyclerView(binding?.rvFavTvShow)

        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            viewModel = ViewModelProvider(this, factory)[FavoriteTvShowViewModel::class.java]

            adapter = FavoriteTvShowAdapter()
            binding?.progressBar

            binding?.progressBar?.visibility = View.VISIBLE
            viewModel.getFavTvShow().observe(this, { tvShow ->
                binding?.progressBar?.visibility = View.GONE
                adapter.submitList(tvShow)
            })

            binding?.rvFavTvShow?.layoutManager = LinearLayoutManager(context)
            //binding?.rvFavTvShow?.setHasFixedSize(true)
            binding?.rvFavTvShow?.adapter = adapter
        }
    }

    private val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.Callback() {
        override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int =
            makeMovementFlags(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)

        override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean = true

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            if (view != null) {
                val swipedPosition = viewHolder.adapterPosition
                val dataEntity = adapter.getSwipedData(swipedPosition)
                dataEntity?.let { viewModel.setTvShowFavorited(it)}

                val snackbar = Snackbar.make(view as View, R.string.message_undo, Snackbar.LENGTH_LONG)
                snackbar.setAction(R.string.message_done) { _ ->
                    dataEntity?.let { viewModel.setTvShowFavorited(it) }
                }
                snackbar.show()
            }
        }
    })
}
