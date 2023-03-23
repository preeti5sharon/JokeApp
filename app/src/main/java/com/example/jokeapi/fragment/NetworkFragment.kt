package com.example.jokeapi.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.jokeapi.adapter.JokeAdapter
import com.example.jokeapi.databinding.FragmentNetworkBinding
import com.example.jokeapi.viewmodel.NetworkViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NetworkFragment : Fragment() {
    private val jokeAdapter = JokeAdapter()
    private var _binding: FragmentNetworkBinding? = null
    private val viewModel: NetworkViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentNetworkBinding.inflate(inflater)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setNetworkUI()
        setAdapter()
        observeFlowData()
    }

    private fun setNetworkUI() {
        _binding?.progressBar?.isVisible = true
        _binding?.swipeRefresh?.setOnRefreshListener {
            viewModel.fetchInitialData()
            _binding?.swipeRefresh?.isRefreshing = false

            _binding?.progressBar?.isVisible = true
        }
    }

    private fun setAdapter() {
        _binding?.recyclerView?.adapter = jokeAdapter
    }

    private fun observeFlowData() {
        lifecycleScope.launch {
            viewModel.jokeFlowData.collect { jokes ->
                jokeAdapter.jokeList = jokes
                jokeAdapter.notifyDataSetChanged()
                _binding?.progressBar?.isVisible = false
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
