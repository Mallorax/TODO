package pl.patrykzygo.todo.repository

import androidx.lifecycle.LiveData
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
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun receiveAllTasks(): List<Task> =
        dao.getAll().map { it.asDomainModel() }



    override suspend fun getTaskWithId(id: Long): kotlinx.coroutines.flow.Flow<Task> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun deleteSpecificTasks(vararg tasks: Task) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun clearAllTasks() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}