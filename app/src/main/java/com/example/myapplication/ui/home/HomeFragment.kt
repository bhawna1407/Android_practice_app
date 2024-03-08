package com.example.myapplication.ui.home

import AlbumsAdapter
import ArtistsAdapter
import ArtistsClickListener
import SearchAdapter
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.Constants
import com.example.myapplication.DetailsActivity
import com.example.myapplication.databinding.FragmentHomeBinding
import com.example.myapplication.ui.TracksAdapter

class HomeFragment : Fragment(), ArtistsClickListener {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val recyclerView = binding.albumsRecyclerView
        val adapter = AlbumsAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false)

        val tracksRecyclerView = binding.popularTracksRecyclerView
        val tracksAdapter = TracksAdapter()
        tracksRecyclerView.adapter = tracksAdapter
        tracksRecyclerView.layoutManager = LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false)


        val artistsRecyclerView = binding.artistsRecyclerView
        val artistAdapter = ArtistsAdapter(this)
        artistsRecyclerView.adapter = artistAdapter
        artistsRecyclerView.layoutManager = LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false)

        homeViewModel.albumsLiveData.observe(viewLifecycleOwner) { albums ->
            adapter.setData(albums)
        }
        homeViewModel.trackLiveData.observe(viewLifecycleOwner) { tracks ->
            tracksAdapter.setData(tracks)
        }
        homeViewModel.artistsLiveData.observe(viewLifecycleOwner) {artists ->
            artistAdapter.setData(artists)
        }
        val authToken = Constants.TOKEN
        val albumIds = "2up3OPMp9Tb4dAKM2erWXQ"
        var trackIds = "2up3OPMp9Tb4dAKM2erWXQ"
        val artistIds = "0TnOYISbd1XYRBk9myaseg"
        homeViewModel.getAlbums(authToken, albumIds)
        //homeViewModel.getTracks(authToken, trackIds)
        homeViewModel.getArtists(authToken, artistIds)
        val artistIdsList = listOf("2CIMQHirSU0MQqyYHq0eOx", "57dN52uHvrHOxijzpIgu3E", "1vCWHaC5f2uS3yhpwWbIA6")
        val albumIdsList = listOf("2up3OPMp9Tb4dAKM2erWXQ", "1A2GTWGtFfWp7KSQTwWOyo", "2noRn2Aes5aoNVsU6iWThc")
        homeViewModel.getSeveralArtists(authToken, artistIdsList)
        homeViewModel.getSeveralAlbums(authToken, albumIdsList)

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onArtistClicked(artistId: String ) {
        val intent = Intent(requireContext(), DetailsActivity::class.java)
        intent.putExtra("artistId", artistId)
        intent.putExtra("artistType", "artist")
        startActivity(intent)
    }
}
