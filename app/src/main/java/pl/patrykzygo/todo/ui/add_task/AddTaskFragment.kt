package pl.patrykzygo.todo.ui.add_task

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Layout
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.google.android.material.textfield.TextInputEditText
import pl.patrykzygo.todo.R
import pl.patrykzygo.todo.services.AlarmReceiver
import pl.patrykzygo.todo.database.NotificationType
import pl.patrykzygo.todo.databinding.AddTaskFragmentBinding
import pl.patrykzygo.todo.domain.Task
import pl.patrykzygo.todo.domain.Timestamp
import pl.patrykzygo.todo.ui.task_list.TasksListFragmentDirections
import java.text.SimpleDateFormat
import java.util.*

class AddTaskFragment : Fragment() {

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

        Log.v("AddTaskFragment", "onViewCreated")

        binding = AddTaskFragmentBinding.inflate(inflater)
        binding.lifecycleOwner = this
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
            task = readTaskFromInput()
            if (checkValidDate(task.date)) {
                viewModel.insertNewTask(task)
            }
        } else {
            return
        }
        if (task.notificationType == NotificationType.NOTIFICATION_ALARM){
            setAlarm(task.date!!.timestampDate.timeInMillis, task.taskId)
        }else if (task.notificationType == NotificationType.NOTIFICATION_POPUP){
            setNotification(task.date!!.timestampDate.timeInMillis)
        }
    }

    private fun setNotification(time: Long){
        var alarmManager = context?.getSystemService(Context.ALARM_SERVICE)

    }

    private fun setAlarm(time: Long, id: Long){
        val alarmManager: AlarmManager?
        alarmManager = context?.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, AlarmReceiver::class.java)
        intent.putExtra("id", id)
        val alarmIntent: PendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0)


        alarmManager.setExact(
            AlarmManager.RTC_WAKEUP,
            time,
            alarmIntent
        )
        Toast.makeText(context, "Alarm set$id", Toast.LENGTH_LONG).show()
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

    private fun readTaskFromInput(): Task {
        val timestamp = viewModel.formatIntoTimestamp(
            binding.taskDateLayout.editText?.text.toString(),
            binding.taskTimeLayout.editText?.text.toString(),
            binding.taskCycleLayout.editText?.text.toString()
        )
        val notificationType = getSelectedNotificationType()
        return Task(
            binding.taskNameInputEditText.text.toString(),
            binding.taskDescriptionInput.text.toString(),
            timestamp, true, notificationType,
            binding.taskPriorityEditText.text.toString().toInt(),
            binding.taskTagInputEditText.text.toString()
        )
    }

    private fun checkValidDate(selectedDate: Timestamp?): Boolean{
        return if (!viewModel.validateDateInput(selectedDate)){
            binding.taskDateLayout.editText?.error = getString(R.string.date_pick_error_msg)
            false
        }else{
            true
        }
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
        binding.taskDateEditText.setOnClickListener { showDatePickerDialog(it) }
        binding.timePickerImage.setOnClickListener { showTimePickerDialog(it) }
        binding.taskTimeEditText.setOnClickListener { showTimePickerDialog(it) }
        binding.taskCycleEditText.setOnClickListener {showCycleAlertDialog(it)}
    }

    private fun showCycleAlertDialog(it: View) {
        TaskCycleAlertDialog().show(childFragmentManager, "cycleAlertDialog")
    }

    private fun setUpObservers() {
        viewModel.date.observe(viewLifecycleOwner, Observer {
            setDateAndTimeValues(binding.taskDateEditText, it, "dd/MM/yyyy")
        })

        viewModel.time.observe(viewLifecycleOwner, Observer {
            setDateAndTimeValues(binding.taskTimeEditText, it, "HH:mm")
        })

        viewModel.cycle.observe(viewLifecycleOwner, Observer {
            binding.taskCycleEditText.setText(it)
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