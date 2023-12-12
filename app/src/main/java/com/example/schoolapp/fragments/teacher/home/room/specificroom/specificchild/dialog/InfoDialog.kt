package com.example.schoolapp.fragments.teacher.home.room.specificroom.specificchild.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.domain.models.ChildrenTeacher
import com.example.schoolapp.R
import com.example.schoolapp.databinding.LayoutItemInfoBinding

class InfoDialog(private val childrenTeacher: ChildrenTeacher) : DialogFragment() {

    private var _binding: LayoutItemInfoBinding? = null

    private val binding get() = _binding!!

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            _binding = LayoutItemInfoBinding.inflate(layoutInflater)
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
        binding.txtBtnOkSpecificChild.setOnClickListener {
            dialog?.cancel()
        }
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//        try {
//            // Instantiate the NoticeDialogListener so you can send events to
//            // the host.
//            noticeDialogListener = context as NoticeDialogListener
//        } catch (e: ClassCastException) {
//            // The activity doesn't implement the interface. Throw exception.
//            throw ClassCastException((context.toString() +
//                    " must implement NoticeDialogListener"))
//        }
//
//    }
}