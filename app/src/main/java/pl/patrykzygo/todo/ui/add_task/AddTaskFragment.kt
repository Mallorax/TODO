package pl.patrykzygo.todo.ui.add_task

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
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
        binding.datePickerImage.setOnClickListener { showDatePickerDialog() }
        viewModel.date.observe(this, Observer {
            val format = SimpleDateFormat("dd/MM/yyyy")
            binding.taskDateEditText.setText(format.format(it.time))
        })

        return binding.root
    }

    private fun showDatePickerDialog(){
        val fragment = DatePickerFragment()
        fragment.show(childFragmentManager, "datePicker")

    }


}