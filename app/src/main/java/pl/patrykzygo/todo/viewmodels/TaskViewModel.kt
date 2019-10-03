package pl.patrykzygo.todo.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import pl.patrykzygo.todo.database.TaskDatabase
import pl.patrykzygo.todo.repository.RoomRepositoryImpl
import pl.patrykzygo.todo.repository.TaskRepository

class TaskViewModel(application: Application): AndroidViewModel(application) {

    private val viewModelJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)
    private val database = TaskDatabase.getInstance(application)
    private val repository: TaskRepository = RoomRepositoryImpl(database)
    

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}