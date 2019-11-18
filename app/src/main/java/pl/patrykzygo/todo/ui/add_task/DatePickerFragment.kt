package pl.patrykzygo.todo.ui.add_task

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.databinding.adapters.DatePickerBindingAdapter
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProviders
import pl.patrykzygo.todo.databinding.DateDialogLayoutBinding

class DatePickerFragment: DialogFragment(), DatePickerDialog.OnDateSetListener {

    private lateinit var binding: DateDialogLayoutBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DateDialogLayoutBinding.inflate(inflater)
       return binding.root
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}