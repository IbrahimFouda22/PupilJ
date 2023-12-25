package com.pupilJ.jk.fragments.teacher.home.room.specificroom.specificchild.addactivity

import android.Manifest
import android.app.Activity.RESULT_OK
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
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.pupilJ.domain.models.ActivityType
import com.pupilJ.jk.R
import com.pupilJ.jk.databinding.FragmentAddActivityBinding
import com.pupilJ.jk.util.RealPathUtil
import dagger.hilt.android.AndroidEntryPoint
import java.io.File

@Suppress("DEPRECATION")
@AndroidEntryPoint
class AddActivityFragment : Fragment() {

    companion object{
        const val CAMERA_PERMISSION_CODE = 1
        const val camera = 100
        const val video = 101
    }
    private val viewModel by viewModels<AddActivityViewModel>({this})
    private var show = false
    private val navArgs by navArgs<AddActivityFragmentArgs>()
    private var isCamera = true
    private lateinit var binding: FragmentAddActivityBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentAddActivityBinding.inflate(layoutInflater)

        val list = arrayListOf(
            ActivityType(R.drawable.img_photo, getString(R.string.photo)),
            ActivityType(R.drawable.img_video, getString(R.string.video)),
            ActivityType(R.drawable.img_food, getString(R.string.food)),
            ActivityType(R.drawable.img_nap, getString(R.string.nap)),
            ActivityType(R.drawable.img_potty, getString(R.string.potty)),
            ActivityType(R.drawable.img_note, getString(R.string.note)),
            ActivityType(R.drawable.img_achievement, getString(R.string.achievement)),
            ActivityType(R.drawable.img_custom, getString(R.string.custom)),
            ActivityType(R.drawable.img_med, getString(R.string.meds)),
            ActivityType(R.drawable.img_name_to_face, getString(R.string.nameToFace)),
            ActivityType(R.drawable.img_incidents, getString(R.string.incidents)),
            ActivityType(R.drawable.img_health_check, getString(R.string.healthCheck)),
            ActivityType(R.drawable.img_observation, getString(R.string.observation)),
        )

//        uri = createUri()
//        registerPictureLauncher()

        val adapter = AddActivityAdapter(list, AddActivityOnClickListener {
//            if(it.name == "Photo")
//                checkCameraPermissionAndOpenCamera()
            if(it.name == getString(R.string.photo)) {
                isCamera = true
                checkCameraPermissionAndOpenCamera(isCamera)
            }
            if (it.name == getString(R.string.video)) {
                isCamera = false
                checkCameraPermissionAndOpenCamera(isCamera)
            }
            if (it.name == getString(R.string.food))
                viewModel.navigateToFood()
            if (it.name == getString(R.string.nap))
                viewModel.navigateToNap()
            if (it.name == getString(R.string.potty))
                viewModel.navigateToPotty()
            if (it.name == getString(R.string.note))
                viewModel.navigateToNote()
            if (it.name == getString(R.string.achievement))
                viewModel.navigateToAchievement()
            if (it.name == getString(R.string.custom))
                viewModel.navigateToCustom()
            if (it.name == getString(R.string.meds))
                viewModel.navigateToMeds()
            if (it.name == getString(R.string.nameToFace))
                viewModel.navigateToNameToFace()
            if (it.name == getString(R.string.incidents))
                viewModel.navigateToIncidents()
            if (it.name == getString(R.string.healthCheck))
                viewModel.navigateToHealthCheck()
            if (it.name == getString(R.string.observation))
                viewModel.navigateToObservation()

        })

        viewModel.showResponsePhotoAndVideo.observe(viewLifecycleOwner){
            show = it
        }

        viewModel.addPhotoResponse.observe(viewLifecycleOwner){
            if(show)
                Toast.makeText(context, it.status, Toast.LENGTH_SHORT).show()
        }

        viewModel.navigateToFood.observe(viewLifecycleOwner){
            if(it){
                viewModel.showResponsePhotoAndVideo()
                navigate(AddActivityFragmentDirections.actionAddActivityFragmentToFoodFragment(navArgs.childrenInfo))
                viewModel.navigateToFoodDone()
            }
        }

        viewModel.navigateToNap.observe(viewLifecycleOwner){
            if(it){
                viewModel.showResponsePhotoAndVideo()
                navigate(AddActivityFragmentDirections.actionAddActivityFragmentToNapFragment(navArgs.childrenInfo))
                viewModel.navigateToNapDone()
            }
        }

        viewModel.navigateToPotty.observe(viewLifecycleOwner){
            if(it){
                viewModel.showResponsePhotoAndVideo()
                navigate(AddActivityFragmentDirections.actionAddActivityFragmentToPottyFragment(navArgs.childrenInfo))
                viewModel.navigateToPottyDone()
            }
        }

        viewModel.navigateToNote.observe(viewLifecycleOwner){
            if(it){
                viewModel.showResponsePhotoAndVideo()
                navigate(AddActivityFragmentDirections.actionAddActivityFragmentToNoteFragment(navArgs.childrenInfo))
                viewModel.navigateToNoteDone()
            }
        }

        viewModel.navigateToAchievement.observe(viewLifecycleOwner){
            if(it){
                viewModel.showResponsePhotoAndVideo()
                navigate(AddActivityFragmentDirections.actionAddActivityFragmentToAchievementFragment(navArgs.childrenInfo))
                viewModel.navigateToAchievementDone()
            }
        }

        viewModel.navigateToCustom.observe(viewLifecycleOwner){
            if(it){
                viewModel.showResponsePhotoAndVideo()
                navigate(AddActivityFragmentDirections.actionAddActivityFragmentToCustomFragment(navArgs.childrenInfo))
                viewModel.navigateToCustomDone()
            }
        }

        viewModel.navigateToMeds.observe(viewLifecycleOwner){
            if(it){
                viewModel.showResponsePhotoAndVideo()
                navigate(AddActivityFragmentDirections.actionAddActivityFragmentToMedFragment(navArgs.childrenInfo))
                viewModel.navigateToMedsDone()
            }
        }

        viewModel.navigateToNameToFace.observe(viewLifecycleOwner){
            if(it){
                viewModel.showResponsePhotoAndVideo()
                navigate(AddActivityFragmentDirections.actionAddActivityFragmentToNameToFaceFragment(navArgs.childrenInfo))
                viewModel.navigateToNameToFaceDone()
            }
        }

        viewModel.navigateToIncidents.observe(viewLifecycleOwner){
            if(it){
                viewModel.showResponsePhotoAndVideo()
                navigate(AddActivityFragmentDirections.actionAddActivityFragmentToIncidentsFragment(navArgs.childrenInfo))
                viewModel.navigateToIncidentsDone()
            }
        }

        viewModel.navigateToHealthCheck.observe(viewLifecycleOwner){
            if(it){
                viewModel.showResponsePhotoAndVideo()
                navigate(AddActivityFragmentDirections.actionAddActivityFragmentToHealthCheckFragment(navArgs.childrenInfo))
                viewModel.navigateToHealthCheckDone()
            }
        }

        viewModel.navigateToObservation.observe(viewLifecycleOwner){
            if(it){
                viewModel.showResponsePhotoAndVideo()
                navigate(AddActivityFragmentDirections.actionAddActivityFragmentToObservationFragment(navArgs.childrenInfo))
                viewModel.navigateToObservationDone()
            }
        }


        binding.recyclerAddActivityTeacher.adapter = adapter



        return binding.root
    }

    private fun navigate(navDirections: NavDirections) {
        findNavController().navigate(navDirections)
    }

    private fun checkCameraPermissionAndOpenCamera(isCamera: Boolean) {
        if (ContextCompat.checkSelfPermission(requireActivity().applicationContext, Manifest.permission.CAMERA)
            == PackageManager.PERMISSION_GRANTED
        ) {
            openCamera(isCamera)
            //requestPermissions(arrayOf(android.Manifest.permission.CAMERA),CAMERA_PERMISSION_CODE)
        }else if (ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(),
            Manifest.permission.CAMERA)) {
            requestPermissions(
                arrayOf(Manifest.permission.CAMERA),
                CAMERA_PERMISSION_CODE
            )
        } else {
                //request the permission
                requestPermissions(
                    arrayOf(Manifest.permission.CAMERA),
                    CAMERA_PERMISSION_CODE
                )
            }
    }



    private fun openCamera(isCamera:Boolean){
        if (isCamera)
            startActivityForResult(Intent(MediaStore.ACTION_IMAGE_CAPTURE),camera)
        else
            startActivityForResult(Intent(MediaStore.ACTION_VIDEO_CAPTURE),video)
    }

    @Deprecated("Deprecated in Java")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == CAMERA_PERMISSION_CODE){
            if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                openCamera(isCamera)
            }else {
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
        if(requestCode == camera && resultCode == RESULT_OK){
           // val uri = data!!.data
            //val path = RealPathUtil
            val bitMap = data?.extras?.get("data") as Bitmap
            //val uri = data.d
            //Log.d("image", convertBitmapToUri(bitMap).toString())
            //saveBitmap(bitMap)
            viewModel.uploadImage(bitMap,navArgs.childrenInfo.id.toString())

        }
        if(requestCode == video && resultCode == RESULT_OK){
            val file = File(RealPathUtil.getRealPath(requireContext(), data?.data!!)!!)
            viewModel.uploadVideo(file,navArgs.childrenInfo.id.toString())
        }

    }

}