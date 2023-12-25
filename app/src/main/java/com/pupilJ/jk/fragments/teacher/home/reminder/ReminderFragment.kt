package com.pupilJ.jk.fragments.teacher.home.reminder

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.pupilJ.domain.models.Reminder
import com.pupilJ.jk.R
import com.pupilJ.jk.databinding.FragmentReminderBinding
import com.pupilJ.jk.util.back
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReminderFragment : Fragment() {

    private lateinit var binding: FragmentReminderBinding
    private val viewModel by viewModels<ReminderViewModel>({ this })
    private var showMsg = false
    var list: List<Reminder>? = null
    private var idDeleted: Int? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentReminderBinding.inflate(layoutInflater)

        binding.btnAddReminderTeacher.setOnClickListener {
            viewModel.navigateToRoom()
        }
        binding.btnBackReminderTeacher.setOnClickListener {
            back(findNavController())
        }

        val adapter = ReminderAdapter(ReminderOnClickListener {reminderId,position->
            //Toast.makeText(context, reminderId.toString() + position.toString(), Toast.LENGTH_SHORT).show()
            viewModel.deleteReminder(reminderId)
            idDeleted = position
        })

        viewModel.showReminderMsg.observe(viewLifecycleOwner) {
            showMsg = it
        }
        viewModel.deleteReminder.observe(viewLifecycleOwner) {
            if (it.status == "success") {
               // adapter.submitList(arrayListOf())
                viewModel.getReminders()
//                if (list != null && idDeleted != null) {
//                   //adapter.notifyItemRemoved(idDeleted!!)
//                    list = listOf(adapter.currentList.removeAt(idDeleted!!))
//                    adapter.submitList(list)
                //}
                if (showMsg)
                    Toast.makeText(context, it.msg, Toast.LENGTH_SHORT).show()
            }
        }
        binding.recyclerReminder.adapter = adapter
        viewModel.reminders.observe(viewLifecycleOwner) {
            if (it.isEmpty())
                showNoData()
            else
                showData()
            list = it
            adapter.submitList(it)
        }

        viewModel.navigateToRoom.observe(viewLifecycleOwner) {
            if (it) {
                viewModel.showReminderMsg()
                findNavController().navigate(ReminderFragmentDirections.actionReminderFragmentToRoomReminderFragment())
                viewModel.navigateToRoomDone()
            }
        }
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        val bottomNav = requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavParent)
        bottomNav.visibility = View.GONE
        binding.recyclerReminder.visibility = View.INVISIBLE
        binding.imgReminder.visibility = View.INVISIBLE
        binding.txtHeadUnderReminder.visibility = View.INVISIBLE
        binding.txtUnderHeadReminder.visibility = View.INVISIBLE
    }

    private fun showNoData() {
        binding.recyclerReminder.visibility = View.INVISIBLE
        binding.imgReminder.visibility = View.VISIBLE
        binding.txtHeadUnderReminder.visibility = View.VISIBLE
        binding.txtUnderHeadReminder.visibility = View.VISIBLE
    }

    private fun showData() {
        binding.recyclerReminder.visibility = View.VISIBLE
        binding.imgReminder.visibility = View.INVISIBLE
        binding.txtHeadUnderReminder.visibility = View.INVISIBLE
        binding.txtUnderHeadReminder.visibility = View.INVISIBLE
    }
}