package pl.patrykzygo.todo.repository

import androidx.lifecycle.LiveData
import pl.patrykzygo.todo.database.Task

interface TaskRepository {

    fun insertTask(vararg tasks: Task)
    fun receiveAllTasks():LiveData<List<Task>>
    fun getTaskWithId(id: Long):LiveData<Task>
    fun deleteSpecificTasks(vararg tasks: Task)
    fun clearAllTasks()

}