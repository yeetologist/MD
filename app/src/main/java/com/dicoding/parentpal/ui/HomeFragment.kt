package com.dicoding.parentpal.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.dicoding.parentpal.R
import com.dicoding.parentpal.databinding.FragmentHomeBinding
import com.dicoding.parentpal.util.PreferenceManager


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var preferenceManager: PreferenceManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        preferenceManager = PreferenceManager(requireContext())

        binding.tvUsername.text = preferenceManager.getPreferences()?.name ?: ""

        binding.cvMain.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_calculatorFragment)
        }

    }
}