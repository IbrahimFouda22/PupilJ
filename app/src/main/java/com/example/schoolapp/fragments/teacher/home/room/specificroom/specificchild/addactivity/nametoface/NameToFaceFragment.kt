package com.example.schoolapp.fragments.teacher.home.room.specificroom.specificchild.addactivity.nametoface

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.domain.models.AdditionalFieldNameToFace
import com.example.schoolapp.R
import com.example.schoolapp.databinding.FragmentNameToFaceBinding
import com.example.schoolapp.fragments.teacher.home.room.specificroom.specificchild.addactivity.AddActivityFragment
import com.example.schoolapp.fragments.teacher.home.room.specificroom.specificchild.addactivity.achievement.AchievementFragment
import com.example.schoolapp.util.back
import com.example.schoolapp.util.getTimeNow
import dagger.hilt.android.AndroidEntryPoint

@Suppress("DEPRECATION")
@AndroidEntryPoint
class NameToFaceFragment : Fragment() {
    private lateinit var binding: FragmentNameToFaceBinding
    private val navArgs by navArgs<NameToFaceFragmentArgs>()
    private val viewModel: NameToFaceViewModel by viewModels({ this })
    private var image: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentNameToFaceBinding.inflate(layoutInflater)
        binding.children = navArgs.childrenInfo
        binding.txtTimeNameToFace2Teacher.text = getTimeNow("EEE,MMM d,'at' HH:mm aaa")

        binding.btnAddActivityNameToFaceTeacher.setOnClickListener {
            viewModel.addNameToFace(
                "name_to_face",
                getAdditionalField(),
                navArgs.childrenInfo.id,
                getTimeNow("yyyy-MM-dd"),
                binding.txtAreaNameToFaceTeacher.text.toString(),
                image
            )
        }
        viewModel.addNameToFaceResponse.observe(viewLifecycleOwner){
            Toast.makeText(context, it.status, Toast.LENGTH_SHORT).show()
        }
        binding.imgBtnCameraNameToFace.setOnClickListener {
            checkCameraPermissionAndOpenCamera()
        }

        binding.btnBackNameToFaceTeacher.setOnClickListener {
            back(findNavController())
        }

        return binding.root
    }

    private fun getAdditionalField(): AdditionalFieldNameToFace {
        val additionalFieldNameToFace = AdditionalFieldNameToFace(
            binding.chipNameToFaceType.checkedChipId.toString(),
        )
        if(binding.chipNameToFaceType.checkedChipId == R.id.chipAway_NameToFace)
            additionalFieldNameToFace.type = "away"
        if(binding.chipNameToFaceType.checkedChipId == R.id.chipHere_NameToFace)
            additionalFieldNameToFace.type = "here"


        return additionalFieldNameToFace
    }

    private fun checkCameraPermissionAndOpenCamera() {
        if (ContextCompat.checkSelfPermission(
                requireActivity().applicationContext,
                Manifest.permission.CAMERA
            )
            == PackageManager.PERMISSION_GRANTED
        ) {
            openCamera()
            //requestPermissions(arrayOf(android.Manifest.permission.CAMERA),CAMERA_PERMISSION_CODE)
        } else if (ActivityCompat.shouldShowRequestPermissionRationale(
                requireActivity(),
                Manifest.permission.CAMERA
            )
        ) {
            requestPermissions(
                arrayOf(Manifest.permission.CAMERA),
                AddActivityFragment.CAMERA_PERMISSION_CODE
            )
        } else {
            //request the permission
            requestPermissions(
                arrayOf(Manifest.permission.CAMERA),
                AddActivityFragment.CAMERA_PERMISSION_CODE
            )
        }
    }

    private fun openCamera() {
        //startActivityForResult(Intent(MediaStore.ACTION_IMAGE_CAPTURE).setType("image/*"),camera)
        startActivityForResult(
            Intent(MediaStore.ACTION_IMAGE_CAPTURE),
            AddActivityFragment.camera
        )
    }

    @Deprecated("Deprecated in Java")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == AchievementFragment.CAMERA_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openCamera()
            } else {
                Toast.makeText(
                    requireActivity().applicationContext,
                    "Camera permission denied, please allow permission to take picture",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == AchievementFragment.camera && resultCode == Activity.RESULT_OK) {
            // val uri = data!!.data
            //val path = RealPathUtil
            val i = data?.extras?.get("data") as Bitmap
            image = i.toString()
            //Log.d("image", convertBitmapToUri(bitMap).toString())
            //viewModel.uploadImage(bitMap)

        }

    }
}