package pl.patrykzygo.todo.repository

import androidx.lifecycle.LiveData
import pl.patrykzygo.todo.database.TaskEntity

interface TaskRepository {

    fun insertTask(vararg taskEntities: TaskEntity)
    fun receiveAllTasks():LiveData<List<TaskEntity>>
    fun getTaskWithId(id: Long):LiveData<TaskEntity>
    fun deleteSpecificTasks(vararg taskEntities: TaskEntity)
    fun clearAllTasks()

}