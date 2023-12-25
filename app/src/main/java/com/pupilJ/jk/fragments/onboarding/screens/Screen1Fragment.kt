package com.pupilJ.jk.fragments.onboarding.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pupilJ.jk.databinding.FragmentScreen1Binding

class Screen1Fragment : Fragment() {
    private lateinit var binding: FragmentScreen1Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentScreen1Binding.inflate(layoutInflater)

        return binding.root
    }

}