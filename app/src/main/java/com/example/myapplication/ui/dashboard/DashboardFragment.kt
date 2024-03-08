package com.example.myapplication.ui.dashboard

import SearchAdapter
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.DetailsActivity
import com.example.myapplication.databinding.FragmentDashboardBinding
import com.example.myapplication.ui.dashboard.DashboardViewModel

class DashboardFragment : Fragment(), SearchAdapter.OnItemClickListener {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: SearchAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root
        recyclerView = binding.recyclerView
        adapter = SearchAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)

        dashboardViewModel.triplesLiveData.observe(viewLifecycleOwner) { nameWithIdAndType ->
            adapter.submitList(nameWithIdAndType)
        }
        dashboardViewModel.searchResultLiveData.observe(viewLifecycleOwner) { searchResult ->
            Toast.LENGTH_SHORT
        }
        dashboardViewModel.errorLiveData.observe(viewLifecycleOwner) { error ->
            Toast.LENGTH_LONG
        }


        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    val authToken = "Bearer BQDQFIjH83Qk0cTrF_Te6BuMC_3CHtc34741-SatUzPOzEehmU9eqGbAsdTWGTMjqWZCM6KCzoPTIup7YDTRGdO8RSW5mb45Ps9PSVxZwxRdEt_uJKc" // Replace with your access token
                    dashboardViewModel.searchSpotify(authToken, query)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                return false
            }
        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClick(name: String, id: String, type: String) {
        val intent = Intent(requireContext(), DetailsActivity::class.java)
        intent.putExtra("name", name)
        intent.putExtra("id", id)
        intent.putExtra("type", type)
        startActivity(intent)
    }
}
