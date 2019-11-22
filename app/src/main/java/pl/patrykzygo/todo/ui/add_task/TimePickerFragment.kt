package pl.patrykzygo.todo.ui.add_task

import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.text.format.DateFormat
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProviders
import java.util.*

class TimePickerFragment: DialogFragment(), TimePickerDialog.OnTimeSetListener {


    private val viewModel: AddTaskViewModel by lazy {
        ViewModelProviders.of(parentFragment!!).get(AddTaskViewModel::class.java)
    }


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val c = Calendar.getInstance()
        val hourOfDay = c.get(Calendar.HOUR_OF_DAY)
        val minutes = c.get(Calendar.MINUTE)
        return TimePickerDialog(parentFragment!!.context, this, hourOfDay, minutes, true)
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        val c = Calendar.getInstance()
        c.set(Calendar.HOUR_OF_DAY, hourOfDay)
        c.set(Calendar.MINUTE, minute)
        viewModel.setTime(c)
    }
}