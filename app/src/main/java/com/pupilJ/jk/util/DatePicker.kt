package com.pupilJ.jk.util

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.DatePicker
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import java.text.DateFormat
import java.util.Calendar


class DatePicker(private val txtView: TextView) : DialogFragment(),
    DatePickerDialog.OnDateSetListener {
    //    companion object{
//        lateinit var selectedDate:String
//    }
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // Use the current date as the default date in the picker
        val c = Calendar.getInstance()
        val year = c[Calendar.YEAR]
        val month = c[Calendar.MONTH]
        val day = c[Calendar.DAY_OF_MONTH]

        // Create a new instance of DatePickerDialog and return it
        return DatePickerDialog(requireActivity(), this, year, month, day)
    }

    override fun onDateSet(view: DatePicker, year: Int, month: Int, day: Int) {
        // Do something with the date chosen by the user
        val mCalendar = Calendar.getInstance()
        mCalendar[Calendar.YEAR] = year
        mCalendar[Calendar.MONTH] = month
        mCalendar[Calendar.DAY_OF_MONTH] = day
        val selected =
            DateFormat.getDateInstance(DateFormat.MONTH_FIELD).format(mCalendar.time)

        //val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        txtView.text = selected
        dateSetListener?.processDatePickerResult(year, month, day)
    }

    private var dateSetListener: IDateSetListener? = null

    fun setIDateSetListener(listener: IDateSetListener?) {
        dateSetListener = listener
    }
    interface IDateSetListener  {
        fun processDatePickerResult(year: Int, month: Int, day: Int)
    }

}
