package pl.patrykzygo.todo.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.flow.Flow
import pl.patrykzygo.todo.database.TaskEntity
import pl.patrykzygo.todo.domain.Task

interface TaskRepository {

    suspend fun insertTask(vararg tasks: Task)
    fun receiveAllTasks(): LiveData<List<Task>>
    fun getTaskWithId(id: Long): LiveData<Task>
    suspend fun deleteSpecificTasks(vararg tasks: Task)
    suspend fun clearAllTasks()

}