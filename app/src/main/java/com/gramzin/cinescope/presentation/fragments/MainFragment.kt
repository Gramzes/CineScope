package com.gramzin.cinescope.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.gramzin.cinescope.R
import com.gramzin.cinescope.databinding.MainFragmentBinding

class MainFragment : Fragment() {
    lateinit var binding: MainFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MainFragmentBinding.inflate(inflater, container, false)

        val navView = binding.bottomNavigationView
        val navController = childFragmentManager.findFragmentById(R.id.child_nav_host_fragment)!!.findNavController()
        navView.setupWithNavController(navController)

        return binding.root
    }
}