package com.pupilJ.jk.fragments.teacher.home.room.specificroom.specificchild.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment

import com.pupilJ.domain.models.ChildrenTeacher
import com.pupilJ.jk.R
import com.pupilJ.jk.databinding.LayoutItemContactBinding

class ContactDialog(private val childrenTeacher: ChildrenTeacher) : DialogFragment() {

    private var _binding: LayoutItemContactBinding? = null

    private val binding get() = _binding!!

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            _binding = LayoutItemContactBinding.inflate(layoutInflater)
            val builder = AlertDialog.Builder(it)
            builder.setView(binding.root)
            val dialog = builder.create()
            dialog.window?.setBackgroundDrawableResource(R.drawable.shape_card_info)
            dialog
        }?: throw IllegalStateException("Activity cannot be null")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding.childrenInfo = childrenTeacher
        binding.txtBtnOkContactSpecificChild.setOnClickListener {
            dialog?.cancel()
        }
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}