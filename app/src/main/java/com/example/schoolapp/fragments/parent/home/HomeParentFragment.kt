package com.example.schoolapp.fragments.parent.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.schoolapp.R
import com.example.schoolapp.databinding.FragmentHomeParentBinding

import com.google.android.material.bottomnavigation.BottomNavigationView

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
//        first = getSharedPref(requireActivity().applicationContext).getString("first", null).toString()
//        last = getSharedPref(requireActivity().applicationContext).getString("last", null).toString()
//        email = getSharedPref(requireActivity().applicationContext).getString("email", null).toString()
//        phone = getSharedPref(requireActivity().applicationContext).getString("phone", null).toString()
//        name = getSharedPref(requireActivity().applicationContext).getString("name", null).toString()
//        photo = getSharedPref(requireActivity().applicationContext).getString("photo", null).toString()
    }

}