package pl.patrykzygo.todo.ui.task_list

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagedList
import androidx.paging.toLiveData
import kotlinx.coroutines.*
import pl.patrykzygo.todo.database.TaskDatabase
import pl.patrykzygo.todo.database.TaskDatabaseDao
import pl.patrykzygo.todo.domain.Task
import pl.patrykzygo.todo.repository.RoomRepositoryImpl
import pl.patrykzygo.todo.repository.TaskRepository

class TaskListViewModel(application: Application): AndroidViewModel(application) {

    private val database = TaskDatabase.getInstance(application)
    private val dao = database.taskDatabaseDao
    private val tasksRepo: TaskRepository = RoomRepositoryImpl(dao)


    //TODO it's temporary shortcut for testing should move it to repo later
    val allTasks = tasksRepo.getAllTasksPaging(dao.getAllPaging())




    override fun onCleared() {
        super.onCleared()
    }
}