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
import com.example.jokeapi.adapter.JokePagingAdapter
import com.example.jokeapi.databinding.FragmentDatabaseBinding
import com.example.jokeapi.viewmodel.DatabaseViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DatabaseFragment : Fragment() {
    private val jokeAdapter = JokePagingAdapter()
    private var _binding: FragmentDatabaseBinding? = null
    private val viewModel: DatabaseViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentDatabaseBinding.inflate(inflater)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding?.recyclerView?.adapter = jokeAdapter

        lifecycleScope.launch {
            viewModel.fetchInitialData().collect { jokes ->
                jokeAdapter.submitData(jokes)
            }
        }
        jokeAdapter.addLoadStateListener {
            val loading =
                it.refresh is LoadState.Loading || it.append is LoadState.Loading || it.prepend is LoadState.Loading
            _binding?.progressBar?.isVisible = loading
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
