package pl.patrykzygo.todo.ui.task_details

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import pl.patrykzygo.todo.database.TaskDatabase
import pl.patrykzygo.todo.repository.RoomRepositoryImpl
import pl.patrykzygo.todo.repository.TaskRepository

class TaskDetailsViewModel(application: Application, taskId: Long): AndroidViewModel(application) {

    private val database = TaskDatabase.getInstance(application)
    private val tasksRepo: TaskRepository = RoomRepositoryImpl(database.taskDatabaseDao)

    val task = tasksRepo.getTaskWithId(taskId)



}