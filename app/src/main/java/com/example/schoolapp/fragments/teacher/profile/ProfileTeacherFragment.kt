package com.example.schoolapp.fragments.teacher.profile

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.domain.models.UserProfile
import com.example.schoolapp.MainActivity
import com.example.schoolapp.R
import com.example.schoolapp.databinding.FragmentProfileTeacherBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class ProfileTeacherFragment : Fragment() {

    private lateinit var binding:FragmentProfileTeacherBinding
    private val viewModel by viewModels<ProfileTeacherViewModel>({this})
    @Inject
    lateinit var sharedPreferences: SharedPreferences
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentProfileTeacherBinding.inflate(layoutInflater)

        binding.profile = UserProfile(
            sharedPreferences.getString("email","")!!,
            sharedPreferences.getString("photo","")!!,
            sharedPreferences.getString("name","")!!,
            sharedPreferences.getString("phoneNum","")!!
        )
        binding.btnLogoutProfileTeacher.setOnClickListener {
            removeUser()
            viewModel.navigateToJoinAs()
        }
        viewModel.navigateToJoinAs.observe(viewLifecycleOwner){
            if(it){
                val nav = requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavParent)
                nav.visibility = View.INVISIBLE
                startActivity(Intent(requireActivity(),MainActivity::class.java))
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