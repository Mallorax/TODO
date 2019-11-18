package pl.patrykzygo.todo.ui.add_task

import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProviders
import pl.patrykzygo.todo.databinding.TimePickerDialogLayoutBinding
import java.util.*

class TimePickerFragment: DialogFragment(), TimePickerDialog.OnTimeSetListener {

    private lateinit var binding: TimePickerDialogLayoutBinding
    private val viewModel: AddTaskViewModel by lazy {
        ViewModelProviders.of(parentFragment!!).get(AddTaskViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = TimePickerDialogLayoutBinding.inflate(inflater)
        return binding.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val c = Calendar.getInstance()
        val hourOfDay = c.get(Calendar.HOUR_OF_DAY)
        val minutes = c.get(Calendar.MINUTE)
        return TimePickerDialog(parentFragment!!.context, this, hourOfDay, minutes, DateFormat.is24HourFormat(context))
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        val c = Calendar.getInstance()
        c.set(Calendar.HOUR_OF_DAY, hourOfDay)
        c.set(Calendar.MINUTE, minute)
        viewModel.setTime(c)
    }
}