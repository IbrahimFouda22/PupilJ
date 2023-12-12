package com.example.schoolapp.fragments.parent.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.schoolapp.R
import com.example.schoolapp.databinding.FragmentHomeParentBinding
import com.example.schoolapp.databinding.FragmentProfileParentBinding
import com.example.schoolapp.util.email
import com.example.schoolapp.util.first
import com.example.schoolapp.util.last
import com.example.schoolapp.util.name
import com.example.schoolapp.util.phone
import com.example.schoolapp.util.photo
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileParentFragment : Fragment() {
    private lateinit var binding: FragmentProfileParentBinding
    private val viewModel by viewModels<ProfileParentViewModel>({ this })
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileParentBinding.inflate(layoutInflater)

//        viewModel.user.observe(viewLifecycleOwner){
////            binding.txtUnderHeadProfile.text = name
////            binding.edtEmailProfile.setText(email)
////            binding.edtPhoneNoProfile.setText(phone)
////            binding.edtFirstNameProfile.setText(first)
////            binding.edtLastNameProfile.setText(last)
////            Picasso.get().load(photo).into(binding.imgProfileProfile)
//        }
        return binding.root
    }
}