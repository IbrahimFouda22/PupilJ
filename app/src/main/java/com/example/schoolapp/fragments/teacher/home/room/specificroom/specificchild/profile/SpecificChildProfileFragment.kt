package com.example.schoolapp.fragments.teacher.home.room.specificroom.specificchild.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.schoolapp.R
import com.example.schoolapp.databinding.FragmentSpecificChildProfileBinding
import com.example.schoolapp.fragments.teacher.home.room.specificroom.specificchild.SpecificChildFragmentArgs

class SpecificChildProfileFragment : Fragment() {

    private lateinit var binding:FragmentSpecificChildProfileBinding
    private val navArgs by navArgs<SpecificChildProfileFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSpecificChildProfileBinding.inflate(layoutInflater)
        binding.className = navArgs.className
        binding.children = navArgs.children



        return binding.root
    }

}