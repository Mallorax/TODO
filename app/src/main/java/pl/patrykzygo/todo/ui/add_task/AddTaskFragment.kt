package pl.patrykzygo.todo.ui.add_task

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import pl.patrykzygo.todo.R
import pl.patrykzygo.todo.database.NotificationType
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
            submitNewTask(it)
        }

        return binding.root
    }

    private fun submitNewTask(v: View){
        val prio = binding.taskPriorityEditText.toString()
        Log.v("value", prio)
        val name = binding.taskNameInputEditText.toString()
        Log.v("value", name)
        val tag = binding.taskTagInputEditText.toString()
        Log.v("value", tag)
        var task: Task
        var validated = checkNoEmptyFields(getString(R.string.field_required_message))
        if (validated) {
            task = getTaskFromFields()
        }else{ return }
        when(binding.taskAlarmRadioGroup.checkedRadioButtonId) {
            R.id.task_type_alarm -> {
                task.notificationType = NotificationType.NOTIFICATION_ALARM
                Snackbar.make(v, "Task \""+task.name+ "\" added with alarm", Snackbar.LENGTH_LONG).show()
            }
            R.id.task_type_notification -> {
                task.notificationType = NotificationType.NOTIFICATION_POPUP
                Snackbar.make(v, "Task \""+task.name+ "\" added with notification", Snackbar.LENGTH_LONG).show()
            }
            else -> Snackbar.make(v, "No notification type selected", Snackbar.LENGTH_LONG).show()
        }

    }

    private fun getTaskFromFields(): Task{
        val dateString = binding.taskDateEditText.text.toString() + " " + binding.taskTimeEditText.text.toString()
        val c = Calendar.getInstance()
        c.time = SimpleDateFormat("dd/MM/yyyy HH:mm").parse(dateString)
        return Task(
            0, binding.taskNameInputEditText.text.toString(),
            binding.taskDescriptionInput.text.toString(),
            c, true, "",
            binding.taskPriorityEditText.text.toString().toInt(),
            binding.taskTagInputEditText.text.toString()
        )
    }



    private fun checkNoEmptyFields(error: String): Boolean{
        var isNameValid = binding.taskNameInputLayout.editText?.text?.isNotEmpty()
        var isTagValid = binding.taskTagInputLayout.editText?.text?.isNotEmpty()
        var isPriorityValid = binding.taskPriorityInputLayout.editText?.text?.isNotEmpty()
        if (!isNameValid!!){
            binding.taskNameInputLayout.editText?.error = error
        }
        if (!isTagValid!!){
            binding.taskTagInputLayout.editText?.error = error
        }
        if (!isPriorityValid!!){
            binding.taskPriorityInputLayout.editText?.error = error
        }
        return (isNameValid && isPriorityValid && isTagValid)
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