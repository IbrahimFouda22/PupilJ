package com.pupilJ.jk.fragments.parent.profile

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.pupilJ.domain.models.UserProfile
import com.pupilJ.jk.MainActivity
import com.pupilJ.jk.R
import com.pupilJ.jk.databinding.FragmentProfileParentBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ProfileParentFragment : Fragment() {
    private lateinit var binding: FragmentProfileParentBinding
    private val viewModel by viewModels<ProfileParentViewModel>({ this })
    @Inject
    lateinit var sharedPreferences: SharedPreferences
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileParentBinding.inflate(layoutInflater)

        binding.profile = UserProfile(
            sharedPreferences.getString("email","")!!,
            sharedPreferences.getString("photo","")!!,
            sharedPreferences.getString("name","")!!,
            sharedPreferences.getString("phoneNum","")!!
        )
        binding.btnLogoutProfileParent.setOnClickListener {
            removeUser()
            viewModel.navigateToJoinAs()
        }
        viewModel.navigateToJoinAs.observe(viewLifecycleOwner){
            if(it){
                val nav = requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavParent)
                nav.visibility = View.INVISIBLE
                startActivity(Intent(requireActivity(), MainActivity::class.java))
                requireActivity().finish()
                viewModel.navigateToJoinAsDone()
            }
        }

        return binding.root
    }

    private fun removeUser() {

        sharedPreferences.edit().putString("token", null).apply()
        sharedPreferences.edit().putString("actor", null).apply()
        sharedPreferences.edit().putInt("schoolId", -1).apply()
        sharedPreferences.edit().putString("email", null).apply()
        sharedPreferences.edit().putString("name", null).apply()
        sharedPreferences.edit().putString("phoneNum", null).apply()
        sharedPreferences.edit().putString("photo", null).apply()
    }
}