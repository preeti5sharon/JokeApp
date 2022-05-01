package com.example.jokeapi.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.example.jokeapi.JokeAdapter
import com.example.jokeapi.JokePagingAdapter
import com.example.jokeapi.viewmodel.MainViewModel
import com.example.jokeapi.api.AppModule
import com.example.jokeapi.databinding.FragmentFirstBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint

class FirstFragment : Fragment() {
    private val jokeAdapter = JokePagingAdapter()
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
        _binding?.recyclerView?.adapter = jokeAdapter
        viewModel.fetchInitialData().observe(viewLifecycleOwner) { jokes ->
          lifecycleScope.launch{
              jokeAdapter.submitData(jokes)
          }
        }
        jokeAdapter.addLoadStateListener {
            val loading =
            it.refresh is LoadState.Loading || it.append is LoadState.Loading || it.prepend is LoadState.Loading
            _binding?.progressBar?.isVisible=loading
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}