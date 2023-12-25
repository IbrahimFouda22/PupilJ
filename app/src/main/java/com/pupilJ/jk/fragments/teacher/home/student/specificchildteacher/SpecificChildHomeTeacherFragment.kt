package com.pupilJ.jk.fragments.teacher.home.student.specificchildteacher

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.pupilJ.jk.R
import com.pupilJ.jk.databinding.FragmentSpecificChildHomeTeacherBinding
import com.pupilJ.jk.databinding.FragmentSpecificChildProfileBinding
import com.pupilJ.jk.databinding.FragmentSpecificRoomHomeTeacherBinding
import com.pupilJ.jk.fragments.teacher.home.room.specificroom.specificchild.profile.SpecificChildProfileFragmentArgs
import com.pupilJ.jk.util.back

class SpecificChildHomeTeacherFragment : Fragment() {
    private lateinit var binding: FragmentSpecificChildHomeTeacherBinding
    private val navArgs by navArgs<SpecificChildHomeTeacherFragmentArgs>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSpecificChildHomeTeacherBinding.inflate(layoutInflater)
        binding.className = navArgs.className
        binding.children = navArgs.children

        binding.txtCloseSpecificChildrenProfile.setOnClickListener {
            back(findNavController())
        }

        return binding.root
    }

}