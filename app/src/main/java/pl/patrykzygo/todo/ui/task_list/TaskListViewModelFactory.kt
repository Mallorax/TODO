package pl.patrykzygo.todo.ui.task_list

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class TaskListViewModelFactory(val app: Application): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(TaskListViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return TaskListViewModel(app) as T
        }
        throw IllegalArgumentException("Cannot construct viewmodel")
    }
}