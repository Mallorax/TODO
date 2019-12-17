package pl.patrykzygo.todo.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import org.intellij.lang.annotations.Flow
import pl.patrykzygo.todo.database.TaskDatabase
import pl.patrykzygo.todo.database.TaskDatabaseDao
import pl.patrykzygo.todo.database.asDomainModel
import pl.patrykzygo.todo.domain.Task
import pl.patrykzygo.todo.domain.toDatabaseEntity

open class RoomRepositoryImpl(private val dao: TaskDatabaseDao): TaskRepository {

    override suspend fun insertTask(vararg tasks: Task) {
        dao.insert(*tasks.map { it.toDatabaseEntity() }.toTypedArray())
    }

    override fun receiveAllTasks(): LiveData<List<Task>> {
        return Transformations.map(dao.getAll()) {
            it.map { t -> t.asDomainModel() }
        }
    }


    override fun getTaskWithId(id: Long): LiveData<Task> {
        return Transformations.map(dao.getWithId(id)) {
            it.asDomainModel()
        }
    }

    override suspend fun deleteSpecificTasks(vararg tasks: Task) {
        dao.deleteTasks(*tasks.map { it.toDatabaseEntity() }.toTypedArray())
    }

    override suspend fun clearAllTasks() {
        dao.clearAllTasks()
    }
}