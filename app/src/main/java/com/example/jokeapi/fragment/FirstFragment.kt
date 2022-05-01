package com.example.jokeapi.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.jokeapi.JokeAdapter
import com.example.jokeapi.viewmodel.MainViewModel
import com.example.jokeapi.api.AppModule
import com.example.jokeapi.databinding.FragmentFirstBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class FirstFragment : Fragment() {
    private val jokeAdapter = JokeAdapter()
    private var _binding: FragmentFirstBinding? = null
    private val viewModel: MainViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFirstBinding.inflate(inflater)
        return _binding?.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchInitialData()
        _binding?.recyclerView?.adapter = jokeAdapter
        viewModel.jokeLiveData.observe(viewLifecycleOwner) { jokes ->
            jokeAdapter.jokeList = jokes
            jokeAdapter.notifyDataSetChanged()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}