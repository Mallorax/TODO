package pl.patrykzygo.todo.ui.task_list

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
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


    init {
        runBlocking {
            tasksRepo.clearAllTasks()
            tasksRepo.insertTask(*createRandomTasks(15).map { t -> t.asDomainModel() }.toTypedArray())
        }
    }


    





    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}