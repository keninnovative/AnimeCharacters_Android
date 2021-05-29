package com.example.animecharacters.fragments.AnimeList

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.animecharacters.R
import com.example.animecharacters.databinding.FragmentAnimeListBinding
import com.example.animecharacters.databinding.FragmentMainBinding
import com.example.animecharacters.fragments.AnimeDetail.AnimeDetailFragment
import com.example.animecharacters.model.APIConstants
import com.example.animecharacters.model.Anime
import com.example.animecharacters.model.AnimeService
import com.example.animecharacters.model.Animes
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


class AnimeListFragment : Fragment() {
    // TODO: Rename and change types of parameters

    private var _binding: FragmentAnimeListBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAnimeListBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = view.findNavController()
        getAnimes()
    }

    fun getAnimes() {
        val retro = Retrofit.Builder()
            .baseUrl(APIConstants.apiURL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
        val service = retro.create(AnimeService::class.java)
        val animeRequest = service.getAnimes("naruto")

        animeRequest.enqueue(object : Callback<Animes> {
            override fun onResponse(call: Call<Animes>, response: Response<Animes>) {
                    response.body()?.let { animes ->
                        binding.recyclerView.apply {
                            setHasFixedSize(true)
                            val animesAdapter = AnimeListAdapter(animes.results)
                            layoutManager = LinearLayoutManager(this.context)
                            adapter = animesAdapter
                            animesAdapter.onItemClick = {

                                val bundle = Bundle()
                                bundle.putParcelable("anime", it)
                                navController.navigate(R.id.action_animeListFragment_to_animeDetailFragment, bundle)
                            }
                        }
                        println(animes)
                    }
            }

            override fun onFailure(call: Call<Animes>, t: Throwable) {
                println(t.message)
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}