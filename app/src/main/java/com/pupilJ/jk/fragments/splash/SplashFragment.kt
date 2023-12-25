package com.pupilJ.jk.fragments.splash

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController

import com.pupilJ.jk.databinding.FragmentSplashBinding
import com.pupilJ.jk.fragments.parent.ParentActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SplashFragment : Fragment() {
    @Inject
    lateinit var sharedPreferences: SharedPreferences
    private var token: String? = null
    private var actor: String? = null
    private var onBoarding = true
    private lateinit var binding: FragmentSplashBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSplashBinding.inflate(layoutInflater)
        token = sharedPreferences.getString("token", null)
        actor = sharedPreferences.getString("actor", null)
        onBoarding = sharedPreferences.getBoolean("onBoard", true)

        if(onBoarding)
            findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToViewPagerFragment())
        else{
            if (token != null) {
                startActivity(
                    Intent(
                        requireActivity(),
                        ParentActivity::class.java
                    ).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP).putExtra("token", token)
                        .putExtra("actor", actor)
                )
                requireActivity().finish()
            } else{
                findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToJoinAsFragment())
            }
        }
        return binding.root
    }

}