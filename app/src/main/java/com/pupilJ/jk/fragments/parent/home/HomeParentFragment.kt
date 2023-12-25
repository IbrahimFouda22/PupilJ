package com.pupilJ.jk.fragments.parent.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController


import com.google.android.material.bottomnavigation.BottomNavigationView
import com.pupilJ.jk.R
import com.pupilJ.jk.databinding.FragmentHomeParentBinding

class HomeParentFragment : Fragment() {
    private lateinit var binding: FragmentHomeParentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeParentBinding.inflate(layoutInflater)

        binding.img2Home.setOnClickListener {
            findNavController().navigate(HomeParentFragmentDirections.actionHomeParentFragmentToMyChildrenFragment())
        }

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        val bottomNav = requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavParent)
        bottomNav.visibility = View.VISIBLE
    }

}