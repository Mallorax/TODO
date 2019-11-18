package pl.patrykzygo.todo.ui.add_task

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import pl.patrykzygo.todo.R

import pl.patrykzygo.todo.databinding.DateDialogLayoutBinding
import java.util.*


class DatePickerFragment: DialogFragment(), DatePickerDialog.OnDateSetListener {

    private lateinit var binding: DateDialogLayoutBinding
    private val viewModel: AddTaskViewModel by lazy {
        ViewModelProviders.of(this.requireParentFragment()).get(AddTaskViewModel::class.java)
    }



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DateDialogLayoutBinding.inflate(inflater)
       return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.confirmDateButton.setOnClickListener {
            findNavController().navigate(R.id.addTaskFragment)
        }
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        val date = Calendar.getInstance()
        date.set(Calendar.YEAR, year)
        date.set(Calendar.MONTH, month)
        date.set(Calendar.DAY_OF_MONTH, dayOfMonth)
        viewModel.setDate(date)
    }

}