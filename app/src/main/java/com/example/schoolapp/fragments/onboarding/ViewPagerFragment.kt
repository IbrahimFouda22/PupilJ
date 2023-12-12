package com.example.schoolapp.fragments.onboarding

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.schoolapp.databinding.FragmentViewPagerBinding
import com.example.schoolapp.fragments.onboarding.screens.Screen1Fragment
import com.example.schoolapp.fragments.onboarding.screens.Screen2Fragment
import com.example.schoolapp.fragments.onboarding.screens.Screen3Fragment
import com.example.schoolapp.fragments.onboarding.screens.Screen4Fragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ViewPagerFragment : Fragment() {
    private lateinit var binding: FragmentViewPagerBinding
    private val viewModel: ViewPagerViewModel by viewModels({this})
    @Inject
    lateinit var sharedPreferences: SharedPreferences
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentViewPagerBinding.inflate(layoutInflater)

        val fragments = arrayListOf(
            Screen1Fragment(),
            Screen2Fragment(),
            Screen3Fragment(),
            Screen4Fragment(),
        )
        val adapter = ViewPagerAdapter(fragments,requireActivity().supportFragmentManager,lifecycle)

        binding.viewPager.adapter = adapter
        binding.dotsIndicator.attachTo(binding.viewPager)


        binding.btnSkip.setOnClickListener {
            viewModel.navigateToJoin()
        }
        viewModel.navigateToJoin.observe(viewLifecycleOwner){
            if(it) {
                sharedPreferences.edit().putBoolean("onBoard", false).apply()
                findNavController().navigate(ViewPagerFragmentDirections.actionViewPagerFragmentToJoinAsFragment())
                viewModel.navigateToJoinDone()
            }

        }

        return binding.root
    }

}