package pl.patrykzygo.todo.ui.add_task

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.NumberPicker
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProviders

class PriorityPickerFragment: DialogFragment(), NumberPicker.OnValueChangeListener {

    private val viewModel: AddTaskViewModel by lazy {
        ViewModelProviders.of(parentFragment!!).get(AddTaskViewModel::class.java)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val picker = NumberPicker(parentFragment!!.context)
        picker.minValue = 1
        picker.maxValue = 5
        picker.setOnValueChangedListener(this)

        val builder = AlertDialog.Builder(parentFragment!!.context)
        builder.setView(picker)
        return builder.create()
    }

    override fun onValueChange(picker: NumberPicker?, oldVal: Int, newVal: Int) {
        viewModel.setPriority(newVal)
    }
}