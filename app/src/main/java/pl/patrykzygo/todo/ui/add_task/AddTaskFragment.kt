package pl.patrykzygo.todo.ui.add_task

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import pl.patrykzygo.todo.R
import pl.patrykzygo.todo.database.NotificationType
import pl.patrykzygo.todo.databinding.AddTaskFragmentBinding
import pl.patrykzygo.todo.domain.Task
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class AddTaskFragment : Fragment() {

    //TODO: There are functions that should be in viewModel -> move them
    //TODO: Make validation for a date fields -> No date pre-current date allowed

    private val viewModel: AddTaskViewModel by lazy {
        ViewModelProviders.of(this).get(AddTaskViewModel::class.java)
    }

    private lateinit var binding: AddTaskFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)

        binding = AddTaskFragmentBinding.inflate(inflater)
        binding.viewModel = viewModel


        setUpListeners()
        setUpObservers()

        binding.submitTaskButton.setOnClickListener {
            submitNewTask(it)
        }

        return binding.root
    }

    private fun submitNewTask(v: View) {
        var task: Task
        var validated = checkNoEmptyFields(getString(R.string.field_required_message))
        if (validated) {
            task = getTaskFromFields()
            viewModel.insertNewTask(task)
            Snackbar.make(v, "New task " +task.name+ " inserted with reminder " +task.notificationType, Snackbar.LENGTH_LONG).show()
        } else {
            return
        }
    }

    private fun getSelectedNotificationType(): String{
        return when (binding.taskAlarmRadioGroup.checkedRadioButtonId) {
            R.id.task_type_alarm -> {
                NotificationType.NOTIFICATION_ALARM
            }
            R.id.task_type_notification -> {
                NotificationType.NOTIFICATION_POPUP
            }
            else -> NotificationType.NOTIFICATION_NONE
        }
    }


    private fun formatIntoCalendar(date: String, time: String): Calendar? {
        var c = Calendar.getInstance()
        return if (date != "" && time != ""){
            val cString = "$date $time"
            c.time = SimpleDateFormat("dd/MM/yyyy HH:mm").parse(cString)
            c
        }else if (date != "" && time == ""){
            c.time = SimpleDateFormat("dd/MM/yyyy").parse(date)
            c.set(Calendar.MINUTE, 1)
            c
        }else if (date == "" && time != ""){
            c.set(Calendar.YEAR, 1970)
            c.time = SimpleDateFormat("HH:mm").parse(time)
            c
        }else {
            null
        }
    }
    private fun getTaskFromFields(): Task {
        val c = formatIntoCalendar(
            binding.taskDateLayout.editText?.text.toString(),
            binding.taskTimeLayout.editText?.text.toString()
        )
        val notificationType = getSelectedNotificationType()
        return Task(
            0, binding.taskNameInputEditText.text.toString(),
            binding.taskDescriptionInput.text.toString(),
            c, true, notificationType,
            binding.taskPriorityEditText.text.toString().toInt(),
            binding.taskTagInputEditText.text.toString()
        )
    }

    private fun checkNoEmptyFields(error: String): Boolean {
        var isNameValid = binding.taskNameInputLayout.editText?.text?.isNotEmpty()
        var isTagValid = binding.taskTagInputLayout.editText?.text?.isNotEmpty()
        var isPriorityValid = binding.taskPriorityInputLayout.editText?.text?.isNotEmpty()
        if (!isNameValid!!) {
            binding.taskNameInputLayout.editText?.error = error
        }
        if (!isTagValid!!) {
            binding.taskTagInputLayout.editText?.error = error
        }
        if (!isPriorityValid!!) {
            binding.taskPriorityInputLayout.editText?.error = error
        }
        return (isNameValid && isPriorityValid && isTagValid)
    }

    private fun setUpListeners() {
        binding.datePickerImage.setOnClickListener { showDatePickerDialog(it) }
        binding.timePickerImage.setOnClickListener { showTimePickerDialog(it) }
    }

    private fun setUpObservers() {
        viewModel.date.observe(viewLifecycleOwner, Observer {
            setDateAndTimeValues(binding.taskDateEditText, it, "dd/MM/yyyy")
        })

        viewModel.time.observe(viewLifecycleOwner, Observer {
            setDateAndTimeValues(binding.taskTimeEditText, it, "HH:mm")
        })


    }

    private fun setDateAndTimeValues(field: TextInputEditText, c: Calendar, pattern: String) {
        val f = SimpleDateFormat(pattern)
        val text = f.format(c.time)
        field.setText(text)
    }

    private fun showTimePickerDialog(v: View) {
        TimePickerFragment().show(childFragmentManager, "timePicker")
    }

    private fun showDatePickerDialog(v: View) {
        DatePickerFragment().show(childFragmentManager, "datePicker")
    }


}