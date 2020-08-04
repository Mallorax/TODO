package pl.patrykzygo.todo.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.DataSource
import androidx.paging.PagedList
import androidx.paging.toLiveData
import pl.patrykzygo.todo.database.TaskDatabaseDao
import pl.patrykzygo.todo.database.TaskEntity
import pl.patrykzygo.todo.domain.Task


open class RoomRepositoryImpl(private val dao: TaskDatabaseDao): TaskRepository {

    override suspend fun insertTask(vararg tasks: Task) {
        dao.insert(*tasks.map { it.toDbModel() }.toTypedArray())
    }

    override fun receiveAllTasks(): LiveData<List<Task>> {
        return Transformations.map(dao.getAll()) {
            it.map { t -> t.toDomainModel() }
        }
    }

    //TODO: It's function for testing purposes
    override fun getAllTasksPaging(source: DataSource.Factory<Int, TaskEntity>): LiveData<PagedList<Task>> {
        return source
            .map { input -> input.toDomainModel() }
            .toLiveData(10)
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