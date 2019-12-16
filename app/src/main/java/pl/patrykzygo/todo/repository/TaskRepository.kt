package pl.patrykzygo.todo.repository

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow
import pl.patrykzygo.todo.database.TaskEntity
import pl.patrykzygo.todo.domain.Task

interface TaskRepository {

    suspend fun insertTask(vararg tasks: Task)
    suspend fun receiveAllTasks(): List<Task>
    suspend fun getTaskWithId(id: Long): Task
    suspend fun deleteSpecificTasks(vararg tasks: Task)
    suspend fun clearAllTasks()

}