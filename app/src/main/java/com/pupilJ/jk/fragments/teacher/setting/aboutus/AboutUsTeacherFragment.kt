package com.pupilJ.jk.fragments.teacher.setting.aboutus

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.pupilJ.jk.R
import com.pupilJ.jk.databinding.FragmentAboutUsTeacherBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AboutUsTeacherFragment : Fragment() {
    private val viewModel by viewModels<AboutUsTeacherViewModel>({ this })
    private lateinit var binding: FragmentAboutUsTeacherBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentAboutUsTeacherBinding.inflate(layoutInflater)
        binding.viewModel = viewModel



        return binding.root
    }

    override fun onStart() {
        super.onStart()
        val bottomNav = requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavParent)
        bottomNav.visibility = View.INVISIBLE
    }

}