package pl.patrykzygo.todo.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import pl.patrykzygo.todo.database.TaskDatabaseDao
import pl.patrykzygo.todo.database.toDomainModel
import pl.patrykzygo.todo.domain.Task
import pl.patrykzygo.todo.domain.toDatabaseEntity

open class RoomRepositoryImpl(private val dao: TaskDatabaseDao): TaskRepository {

    override suspend fun insertTask(vararg tasks: Task) {
        dao.insert(*tasks.map { it.toDbModel() }.toTypedArray())
    }

    override fun receiveAllTasks(): LiveData<List<Task>> {
        return Transformations.map(dao.getAll()) {
            it.map { t -> t.toDomainModel() }
        }
    }


    override fun getTaskWithId(id: Long): LiveData<Task> {
        return Transformations.map(dao.getWithId(id)) {
            it.toDomainModel()
        }
    }

    override suspend fun deleteSpecificTasks(vararg tasks: Task) {
        dao.deleteTasks(*tasks.map { it.toDbModel() }.toTypedArray())
    }

    override suspend fun clearAllTasks() {
        dao.clearAllTasks()
    }
}