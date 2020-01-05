package pl.patrykzygo.todo.ui.task_details

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import pl.patrykzygo.todo.ui.task_list.TaskListViewModel
import java.lang.IllegalArgumentException

class TaskDetailsViewModelFactory(private val app: Application,
                                  private val taskId: Long): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(TaskDetailsViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return TaskDetailsViewModel(app, taskId) as T
        }
        throw IllegalArgumentException("Cannot construct viewmodel")
    }
}