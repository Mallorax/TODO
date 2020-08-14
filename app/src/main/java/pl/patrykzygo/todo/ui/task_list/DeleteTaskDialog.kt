package pl.patrykzygo.todo.ui.task_list

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import pl.patrykzygo.todo.R
import pl.patrykzygo.todo.domain.Task
import java.lang.IllegalStateException

class DeleteTaskDialog(val task: Task) : DialogFragment() {

    private val viewModel: TaskListViewModel by lazy {
        ViewModelProviders.of(parentFragment!!).get(TaskListViewModel::class.java)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setMessage("Are you sure you want to delete this?")
                .setTitle("Delete task")
                .setPositiveButton(getString(R.string.text_delete)) { dialog: DialogInterface, _: Int ->
                    viewModel.deleteTask(task)
                    //Dunno if making snackbar is ok here, but hey, it works!!!
                    Snackbar.make(parentFragment?.view!!, "Task deleted", Snackbar.LENGTH_LONG).show()
                    dialog.dismiss()
                }
                .setNegativeButton(getString(R.string.cancel)) { dialog: DialogInterface, i: Int ->
                    dialog.dismiss()
                }
            builder.create()
        }?: throw IllegalStateException("Activity cannot be null")
    }
}