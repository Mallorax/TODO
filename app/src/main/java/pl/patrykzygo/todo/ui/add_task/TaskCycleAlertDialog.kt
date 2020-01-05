package pl.patrykzygo.todo.ui.add_task

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle

import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import pl.patrykzygo.todo.R
import pl.patrykzygo.todo.domain.Timestamp
import java.lang.IllegalStateException


class TaskCycleAlertDialog: DialogFragment(){

    private val viewModel: AddTaskViewModel by lazy {
        ViewModelProviders.of(parentFragment!!).get(AddTaskViewModel::class.java)
    }

    var checkedItem: Int = 0

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        observeOnCycle()
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setTitle(R.string.cycle_alert_dialog_title)
                .setSingleChoiceItems(R.array.task_cycles, checkedItem){ _, id ->
                    when(id){
                        0 -> viewModel.setCycle(Timestamp.NONE)
                        1 -> viewModel.setCycle(Timestamp.DAILY)
                        2 -> viewModel.setCycle(Timestamp.WEEKLY)
                        3 -> viewModel.setCycle(Timestamp.MONTHLY)
                        4 -> viewModel.setCycle(Timestamp.YEARLY)
                        else -> viewModel.setCycle(Timestamp.NONE)
                    }
                }
                .setPositiveButton(R.string.confirm) { dialog, _ ->
                    dialog.dismiss()
                }
                .setNegativeButton(R.string.cancel) { dialog, _ ->
                    dialog.dismiss()
                }
            builder.create()
        }?: throw IllegalStateException("Activity cannot be null")
    }

    private fun observeOnCycle(){
        viewModel.cycle.observe(parentFragment!!.viewLifecycleOwner, Observer {
            when(it){
                Timestamp.NONE -> checkedItem = 0
                Timestamp.DAILY -> checkedItem = 1
                Timestamp.WEEKLY -> checkedItem = 2
                Timestamp.MONTHLY -> checkedItem = 3
                Timestamp.YEARLY -> checkedItem = 4
                else -> 0
            }
        })
    }
}