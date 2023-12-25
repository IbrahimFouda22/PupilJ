package com.pupilJ.jk.fragments.teacher.home.room.specificroom.specificchild.addactivity.note

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.pupilJ.jk.databinding.FragmentNoteBinding
import com.pupilJ.jk.util.back
import com.pupilJ.jk.util.getTimeNow
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NoteFragment : Fragment() {
    private lateinit var binding: FragmentNoteBinding
    private val navArgs by navArgs<NoteFragmentArgs>()
    private val viewModel: NoteViewModel by viewModels({ this })
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentNoteBinding.inflate(layoutInflater)
        binding.children = navArgs.childrenInfo
        binding.txtTimeNote2Teacher.text = getTimeNow("EEE,MMM d,'at' HH:mm aaa")

        binding.btnAddActivityNoteTeacher.setOnClickListener {
            viewModel.addNote(
                "note",
                navArgs.childrenInfo.id,
                getTimeNow("yyyy-MM-dd HH:mm:ss"),
                binding.txtAreaNoteTeacher.text.toString()
            )
        }
        viewModel.addNoteResponse.observe(viewLifecycleOwner){
            Toast.makeText(context, it.status, Toast.LENGTH_SHORT).show()
        }
        binding.btnBackNoteTeacher.setOnClickListener {
            back(findNavController())
        }

        return binding.root
    }

}