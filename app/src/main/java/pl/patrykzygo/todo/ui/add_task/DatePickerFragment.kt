package pl.patrykzygo.todo.ui.add_task


import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProviders

import java.util.*


class DatePickerFragment: DialogFragment(), DatePickerDialog.OnDateSetListener {

    private val viewModel: AddTaskViewModel by lazy {
        ViewModelProviders.of(parentFragment!!).get(AddTaskViewModel::class.java)
    }



    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        return DatePickerDialog(parentFragment!!.context, this, year, month, day)
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        val date = Calendar.getInstance()
        date.set(Calendar.YEAR, year)
        date.set(Calendar.MONTH, month)
        date.set(Calendar.DAY_OF_MONTH, dayOfMonth)
        viewModel.setDate(date)
    }

}