package pl.patrykzygo.todo.repository

import androidx.lifecycle.LiveData
import pl.patrykzygo.todo.database.TaskEntity
import pl.patrykzygo.todo.domain.Task

interface TaskRepository {

    suspend fun insertTask(vararg tasks: Task)
    suspend fun receiveAllTasks():LiveData<List<Task>>
    suspend fun getTaskWithId(id: Long):LiveData<Task>
    suspend fun deleteSpecificTasks(vararg tasks: Task)
    suspend fun clearAllTasks()

}