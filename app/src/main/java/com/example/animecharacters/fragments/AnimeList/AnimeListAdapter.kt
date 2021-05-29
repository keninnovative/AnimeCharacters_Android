package com.example.animecharacters.fragments.AnimeList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.animecharacters.R
import com.example.animecharacters.databinding.AnimeItemViewBinding
import com.example.animecharacters.model.Anime

class AnimeListAdapter(val animes: List<Anime>): RecyclerView.Adapter<AnimeListAdapter.AnimeViewHolder>() {

    var onItemClick: ((Anime) -> Unit)? = null
    inner class AnimeViewHolder(val binding: AnimeItemViewBinding): RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(animes[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeViewHolder {
        val binding = AnimeItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AnimeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AnimeViewHolder, position: Int) {
        with(holder) {
            with(animes[position]) {
                binding.tvTitle.text = this.title
                binding.tvSynopsis.text = this.synopsis
                Glide.with(binding.root).load(this.image_url).into(binding.imageView)
            }
        }
    }

    override fun getItemCount(): Int {
        return animes.size
    }
}