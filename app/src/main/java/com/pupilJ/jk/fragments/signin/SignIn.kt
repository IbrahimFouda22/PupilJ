package com.pupilJ.jk.fragments.signin

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.pupilJ.jk.databinding.FragmentSignInParentBinding
import com.pupilJ.jk.fragments.parent.ParentActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SignIn : Fragment() {
    private lateinit var binding: FragmentSignInParentBinding
    private val viewModel: SignInViewModel by viewModels()
    private val navArgs by navArgs<SignInArgs>()

    @Inject
    lateinit var sharedPreferences: SharedPreferences
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSignInParentBinding.inflate(layoutInflater)

        binding.btnLogin.setOnClickListener {
            viewModel.showProgress(true)
            viewModel.login(
                binding.edtId.text.toString(),
                binding.edtPassword.text.toString(),
                navArgs.actor
            )
        }

        viewModel.showProgress.observe(viewLifecycleOwner) {
            if (it) {
                binding.circularProgressIndicatorSignIn.visibility = View.VISIBLE
                requireActivity().window.setFlags(
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
                )
            } else {
                binding.circularProgressIndicatorSignIn.visibility = View.INVISIBLE
                requireActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
            }
        }

        viewModel.navigateToHomeParent.observe(viewLifecycleOwner) {
            if (it) {
                //val str = viewModel.user.value!!.name.split(" ")
                binding.circularProgressIndicatorSignIn.visibility = View.INVISIBLE
                Log.d("user", viewModel.user.value.toString())
                Log.d("user", it.toString())
                savePersonalInfo(
                    viewModel.user.value!!.token,
                    navArgs.actor,
                    viewModel.user.value!!.schoolId.toString(),
                    viewModel.user.value!!.email,
                    viewModel.user.value!!.name,
                    viewModel.user.value!!.phone_number,
                    viewModel.user.value!!.profile_photo_url,
                    viewModel.user.value!!.id,
                )
                //sharedPreferences.edit().
                val intent = Intent(requireActivity(), ParentActivity::class.java).putExtra(
                    "actor",
                    navArgs.actor
                )
                startActivity(intent)
                viewModel.navigateToHomeParentDone()
                requireActivity().finish()
            }
        }
        return binding.root
    }

    private fun savePersonalInfo(
        token: String,
        actor: String,
        schoolId: String,
        email: String,
        name: String,
        phoneNum: String?,
        photo: String?,
        id: Int,
    ) {

        sharedPreferences.edit().putString("token", token).apply()
        sharedPreferences.edit().putInt("id",id).apply()
        sharedPreferences.edit().putString("actor", actor).apply()
        sharedPreferences.edit().putString("schoolId", schoolId).apply()
        sharedPreferences.edit().putString("email", email).apply()
        sharedPreferences.edit().putString("name", name).apply()
        sharedPreferences.edit().putString("phoneNum", phoneNum).apply()
        sharedPreferences.edit().putString("photo", photo).apply()

    }
}