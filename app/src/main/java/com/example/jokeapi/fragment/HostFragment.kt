package com.example.jokeapi.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.jokeapi.JokeAdapter
import com.example.jokeapi.R
import com.example.jokeapi.databinding.FragmentFirstBinding
import com.example.jokeapi.databinding.FragmentHostBinding
import com.example.jokeapi.viewmodel.MainViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HostFragment : Fragment() {
    private var _binding: FragmentHostBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHostBinding.inflate(inflater)
        return _binding?.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val firstFragment = FirstFragment()
        val secondFragment = SecondFragment()
        _binding?.run {
            setFragment(firstFragment)
            bottomNav.setOnNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.firstFragment -> {
                        setFragment(firstFragment)
                        // Respond to navigation item 1 click
                        true
                    }
                    R.id.secondFragment -> {
                        setFragment(secondFragment)
                        // Respond to navigation item 2 click
                        true
                    }
                    else -> false
                }
            }
        }
    }

    fun setFragment(fragment: Fragment) = _binding?.run {

        childFragmentManager.beginTransaction().replace(fragmentContainerView.id, fragment)
            .addToBackStack(null).commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}