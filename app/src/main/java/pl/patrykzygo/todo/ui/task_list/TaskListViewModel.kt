package pl.patrykzygo.todo.ui.task_list

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import pl.patrykzygo.todo.database.TaskDatabase
import pl.patrykzygo.todo.database.TaskDatabaseDao
import pl.patrykzygo.todo.domain.Task
import pl.patrykzygo.todo.repository.RoomRepositoryImpl
import pl.patrykzygo.todo.repository.TaskRepository

class TaskListViewModel(application: Application): AndroidViewModel(application) {

    private val database = TaskDatabase.getInstance(application)
    private val tasksRepo: TaskRepository = RoomRepositoryImpl(database.taskDatabaseDao)

    private val _allTasks = MutableLiveData<List<Task>>()
    val allTasks: LiveData<List<Task>>
        get() = _allTasks

    private fun getAllTasks(){
        viewModelScope.launch {
            _allTasks.value = tasksRepo.receiveAllTasks()
        }

    }

    init {
        getAllTasks()
    }




    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}