package pl.patrykzygo.todo.ui.add_task

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.add_task_fragment.*
import pl.patrykzygo.todo.databinding.AddTaskFragmentBinding
import java.text.SimpleDateFormat

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
            val format = SimpleDateFormat("dd/MM/yyyy")
            val text = format.format(it.time)
            binding.taskDateEditText.setText(text)
        })

        return binding.root
    }

    private fun showDatePickerDialog(v: View){
        val fragment = DatePickerFragment()
        fragment.show(childFragmentManager, "datePicker")

    }


}