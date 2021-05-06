package com.kuldeepjoshi.kotlintutorial.retrofit.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kuldeepjoshi.kotlintutorial.databinding.GridViewMarsItemBinding
import com.kuldeepjoshi.kotlintutorial.retrofit.network.MarsPhotos

class PhotoGridAdapter :
    ListAdapter<MarsPhotos, PhotoGridAdapter.MarsPhotoViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarsPhotoViewHolder {

        val biding = GridViewMarsItemBinding.inflate(LayoutInflater.from(parent.context))

        return MarsPhotoViewHolder(biding)
    }

    override fun onBindViewHolder(holder: MarsPhotoViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class MarsPhotoViewHolder(private var binding: GridViewMarsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(marsPhotos: MarsPhotos) {
            binding.photo = marsPhotos
            binding.executePendingBindings()
        }

    }

    companion object DiffCallback : DiffUtil.ItemCallback<MarsPhotos>() {

        override fun areItemsTheSame(oldItem: MarsPhotos, newItem: MarsPhotos): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MarsPhotos, newItem: MarsPhotos): Boolean {
            return oldItem.imgSrcUrl == newItem.imgSrcUrl
        }

    }

}

