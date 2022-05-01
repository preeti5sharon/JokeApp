package com.example.jokeapi.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.jokeapi.JokeAdapter
import com.example.jokeapi.databinding.FragmentFirstBinding
import com.example.jokeapi.databinding.FragmentSecondBinding
import com.example.jokeapi.viewmodel.MainViewModel
import com.example.jokeapi.viewmodel.SecondViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SecondFragment : Fragment() {
    private val jokeAdapter = JokeAdapter()
    private var _binding: FragmentSecondBinding? = null
    private val viewModel: SecondViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSecondBinding.inflate(inflater)
        return _binding?.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding?.progressBar?.isVisible = true
        _binding?.swipeRefresh?.setOnRefreshListener {
            viewModel.fetchInitialData()
            _binding?.swipeRefresh?.isRefreshing = false

            _binding?.progressBar?.isVisible = true
        }
        viewModel.fetchInitialData()
        _binding?.recyclerView?.adapter = jokeAdapter
        viewModel.jokeLiveData.observe(viewLifecycleOwner) { jokes ->
            jokeAdapter.jokeList = jokes
            jokeAdapter.notifyDataSetChanged()
            _binding?.progressBar?.isVisible = false
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}