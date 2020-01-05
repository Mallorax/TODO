package pl.patrykzygo.todo.ui.add_task

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle

import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProviders
import pl.patrykzygo.todo.R
import java.lang.IllegalStateException


class TaskCycleAlertDialog: DialogFragment(){

    private val viewModel: AddTaskViewModel by lazy {
        ViewModelProviders.of(parentFragment!!).get(AddTaskViewModel::class.java)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setTitle(R.string.cycle_alert_dialog_title)
                .setPositiveButton(R.string.confirm) { dialog, id ->

                }
                .setNegativeButton(R.string.cancel) { dialog, id ->

                }
            builder.create()
        }?: throw IllegalStateException("Activity cannot be null")
    }
}