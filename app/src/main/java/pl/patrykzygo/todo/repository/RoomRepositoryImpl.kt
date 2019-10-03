package pl.patrykzygo.todo.repository

import androidx.lifecycle.LiveData
import pl.patrykzygo.todo.database.TaskEntity
import pl.patrykzygo.todo.database.TaskDatabase

class RoomRepositoryImpl(private val db: TaskDatabase): TaskRepository {



    override fun insertTask(vararg taskEntities: TaskEntity) {
        db.taskDatabaseDao.insert(*taskEntities)
    }

    override fun receiveAllTasks(): LiveData<List<TaskEntity>> {
        return db.taskDatabaseDao.getAll()
    }

    override fun getTaskWithId(id: Long): LiveData<TaskEntity> {
        return db.taskDatabaseDao.getWithId(id)
    }

    override fun deleteSpecificTasks(vararg taskEntities: TaskEntity) {
        db.taskDatabaseDao.deleteTasks(*taskEntities)
    }

    override fun clearAllTasks() {
        db.taskDatabaseDao.deleteAllTasks()
    }
}