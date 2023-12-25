package com.pupilJ.jk.fragments.parent.setting.aboutus

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.pupilJ.jk.R
import com.pupilJ.jk.databinding.FragmentAboutUsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AboutUsFragment : Fragment() {
    private val viewModel by viewModels<AboutUsParentViewModel>({ this })
    private lateinit var binding: FragmentAboutUsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentAboutUsBinding.inflate(layoutInflater)
        binding.viewModel = viewModel



        return binding.root
    }

    override fun onStart() {
        super.onStart()
        val bottomNav = requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavParent)
        bottomNav.visibility = View.INVISIBLE
    }
}