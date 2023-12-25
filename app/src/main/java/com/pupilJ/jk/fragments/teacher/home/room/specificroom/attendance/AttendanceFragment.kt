package com.pupilJ.jk.fragments.teacher.home.room.specificroom.attendance

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.pupilJ.domain.models.ChildrenTeacher
import com.pupilJ.jk.R
import com.pupilJ.jk.databinding.FragmentAttendanceBinding
import com.pupilJ.jk.util.changeStatusBarColor
import com.pupilJ.jk.util.getTimeNow
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AttendanceFragment : Fragment() {

    private lateinit var binding: FragmentAttendanceBinding
    private lateinit var list: List<ChildrenTeacher>
    private val selectedList = arrayListOf<ChildrenTeacher>()
    private val navArgs by navArgs<AttendanceFragmentArgs>()
    @Inject lateinit var factory : AttendanceViewModel.AssistedFactory
    private val viewModel : AttendanceViewModel by viewModels {
        AttendanceViewModel.attendanceFactory(factory, navArgs.children.toList())
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentAttendanceBinding.inflate(layoutInflater)
        binding.className = navArgs.className

        val adapter = AttendanceAdapter(AttendanceOnClickListener {
            if (it.isSelected){
                if(!selectedList.contains(it))
                    selectedList.add(it)
            }
            else
                if(selectedList.contains(it))
                    selectedList.remove(it)
        })
        binding.recyclerAttendance.adapter = adapter
        viewModel.children.observe(viewLifecycleOwner){
            adapter.submitList(it)
            list = it
        }

        binding.edtSearchAttendance.addTextChangedListener(textWatcher(adapter))

        binding.constraintCheckInAttendance.setOnClickListener {
            viewModel.setAttendance("check_in",getTimeNow("yyyy-MM-dd HH:mm"),selectedList)
        }

        binding.constraintCheckOutAttendance.setOnClickListener {
            viewModel.setAttendance("check_out",getTimeNow("yyyy-MM-dd HH:mm"),selectedList)
        }

        viewModel.status.observe(viewLifecycleOwner){
            if(it.status == "success")
                viewModel.getChildren(navArgs.classId)
            Toast.makeText(context, it.status, Toast.LENGTH_SHORT).show()
        }
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        changeStatusBarColor(requireActivity(),requireActivity().getColor(R.color.colorPrimary))
    }

    override fun onPause() {
        super.onPause()
        changeStatusBarColor(requireActivity(),requireActivity().getColor(R.color.canary))

    }

    private fun textWatcher(adapter: AttendanceAdapter):TextWatcher{
        return object :TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(!s.isNullOrEmpty()){
                    val filteredList = list.filter {
                        it.name!!.contains(s,ignoreCase = true)
                    }
                    adapter.submitList(filteredList)

                }
            }

            override fun afterTextChanged(s: Editable?) {
                if(s!!.isEmpty())
                    adapter.submitList(list)
            }

        }
    }

}