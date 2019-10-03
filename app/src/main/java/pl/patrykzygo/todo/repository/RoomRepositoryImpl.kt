package pl.patrykzygo.todo.repository

import androidx.lifecycle.LiveData
import pl.patrykzygo.todo.database.Task
import pl.patrykzygo.todo.database.TaskDatabase

class RoomRepositoryImpl(private val db: TaskDatabase): TaskRepository {



    override fun insertTask(vararg tasks: Task) {
        db.taskDatabaseDao.insert(*tasks)
    }

    override fun receiveAllTasks(): LiveData<List<Task>> {
        return db.taskDatabaseDao.getAll()
    }

    override fun getTaskWithId(id: Long): LiveData<Task> {
        return db.taskDatabaseDao.getWithId(id)
    }

    override fun deleteSpecificTasks(vararg tasks: Task) {
        db.taskDatabaseDao.deleteTasks(*tasks)
    }

    override fun clearAllTasks() {
        db.taskDatabaseDao.deleteAllTasks()
    }
}