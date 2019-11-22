package pl.patrykzygo.todo.ui.add_task

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import pl.patrykzygo.todo.R
import pl.patrykzygo.todo.database.NotificationType
import pl.patrykzygo.todo.database.asDomainModel
import pl.patrykzygo.todo.databinding.AddTaskFragmentBinding
import pl.patrykzygo.todo.domain.Task
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

        binding = AddTaskFragmentBinding.inflate(inflater)
        binding.viewModel = viewModel

        setUpListeners()
        setUpObservers()

        binding.submitTaskButton.setOnClickListener {
            val dateString = binding.taskDateEditText.text.toString() + " " + binding.taskTimeEditText.text.toString()
            val c = Calendar.getInstance()
            c.time = SimpleDateFormat("dd/MM/yyyy HH:mm").parse(dateString)
            val task = Task(
                0, binding.taskNameInputEditText.text.toString(),
                binding.taskDescriptionInput.text.toString(),
                c, true, "",
                binding.taskPriorityEditText.text.toString().toInt(),
                binding.taskTagInputEditText.text.toString()
            )
            when(binding.taskAlarmRadioGroup.checkedRadioButtonId) {
                 R.id.task_type_alarm -> {
                     task.notificationType = NotificationType.NOTIFICATION_ALARM
                    viewModel.insertNewTask(task)
                    Snackbar.make(it, "Task "+task.title+ " added with alarm", Snackbar.LENGTH_LONG).show()
                }
                R.id.task_type_notification -> {
                    task.notificationType = NotificationType.NOTIFICATION_POPUP
                    viewModel.insertNewTask(task)
                    Snackbar.make(it, "Task "+task.title+ " added with notification", Snackbar.LENGTH_LONG).show()
                }
                else -> Snackbar.make(it, "No notification type selected", Snackbar.LENGTH_LONG).show()
            }
        }

        return binding.root
    }
    
    private fun setUpListeners(){
        binding.datePickerImage.setOnClickListener { showDatePickerDialog(it) }
        binding.timePickerImage.setOnClickListener { showTimePickerDialog(it) }
    }

    private fun setUpObservers(){
        viewModel.date.observe(viewLifecycleOwner, Observer {
            setDateAndTimeValues(binding.taskDateEditText, it, "dd/MM/yyyy")
        })

        viewModel.time.observe(viewLifecycleOwner, Observer {
            setDateAndTimeValues(binding.taskTimeEditText, it, "HH:mm")
        })

    }


    private fun setDateAndTimeValues(field: TextInputEditText, c: Calendar, pattern: String){
        val f = SimpleDateFormat(pattern)
        val text = f.format(c.time)
        field.setText(text)
    }

    private fun showTimePickerDialog(v: View){
        TimePickerFragment().show(childFragmentManager,"timePicker")
    }

    private fun showDatePickerDialog(v: View){

        DatePickerFragment().show(childFragmentManager, "datePicker")

    }


}