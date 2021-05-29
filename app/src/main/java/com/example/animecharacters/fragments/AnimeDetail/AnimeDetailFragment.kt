package com.example.animecharacters.fragments.AnimeDetail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.example.animecharacters.R
import com.example.animecharacters.databinding.AnimeDetailFragmentBinding
import com.example.animecharacters.databinding.FragmentAnimeListBinding
import com.example.animecharacters.model.Anime

class AnimeDetailFragment : Fragment() {

    private var _binding: AnimeDetailFragmentBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var navController: NavController

    companion object {
        fun newInstance() = AnimeDetailFragment()
    }

    private lateinit var viewModel: AnimeDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = AnimeDetailFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AnimeDetailViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = view.findNavController()
        loadAnime()
    }

    private fun loadAnime() {
        val anime = arguments?.getParcelable<Anime>("anime")
        println("Selected anime: $anime")

        Glide.with(binding.root).load(anime?.image_url).into(binding.imgViewPoster)
        binding.tvTitle.text = anime?.title
        binding.tvSynopsis.text = anime?.synopsis
        binding.tvType.text = anime?.type
        binding.tvEpisodes.text = "Episodes: " + anime?.episodes
        binding.tvRate.text = "Rated: " + anime?.rated
        binding.tvMembers.text = "Members: " + anime?.members
        binding.tvScore.text = "Score: " + anime?.score
    }

}