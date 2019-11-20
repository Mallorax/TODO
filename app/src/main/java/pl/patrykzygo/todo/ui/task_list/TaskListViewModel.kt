package pl.patrykzygo.todo.ui.task_list

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*
import pl.patrykzygo.todo.createRandomTasks
import pl.patrykzygo.todo.database.TaskDatabase
import pl.patrykzygo.todo.database.asDomainModel
import pl.patrykzygo.todo.domain.Task
import pl.patrykzygo.todo.repository.RoomRepositoryImpl
import pl.patrykzygo.todo.repository.TaskRepository

class TaskListViewModel(application: Application): AndroidViewModel(application) {

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private val database = TaskDatabase.getInstance(application)
    private val tasksRepo: TaskRepository = RoomRepositoryImpl(database)

    var allTasks: LiveData<List<Task>> = runBlocking {tasksRepo.receiveAllTasks()}

    //TODO: Get rid of runBlocking block

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}