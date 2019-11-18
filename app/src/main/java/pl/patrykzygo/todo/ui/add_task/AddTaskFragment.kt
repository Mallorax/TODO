package pl.patrykzygo.todo.ui.add_task

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.textfield.TextInputEditText
import pl.patrykzygo.todo.databinding.AddTaskFragmentBinding
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
        binding.datePickerImage.setOnClickListener { showDatePickerDialog(it) }
        viewModel.date.observe(viewLifecycleOwner, Observer {
            setDateAndTimeValues(binding.taskDateEditText, it, "dd/MM/yyyy")
        })

        binding.timePickerImage.setOnClickListener { showTimePickerDialog(it) }

        viewModel.time.observe(viewLifecycleOwner, Observer {
            setDateAndTimeValues(binding.taskTimeEditText, it, "HH:mm")
        })

        return binding.root
    }

    private fun setDateAndTimeValues(field: TextInputEditText, c: Calendar, pattern: String){
        val f = SimpleDateFormat(pattern)
        val text = f.format(c.time)
        field.setText(text)
    }

    private fun showTimePickerDialog(v: View){
        val fragment = TimePickerFragment()
        fragment.show(childFragmentManager,"timePicker")
    }

    private fun showDatePickerDialog(v: View){
        val fragment = DatePickerFragment()
        fragment.show(childFragmentManager, "datePicker")

    }


}