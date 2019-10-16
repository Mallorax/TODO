package pl.patrykzygo.todo.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pl.patrykzygo.todo.database.TaskDatabase
import pl.patrykzygo.todo.database.asDomainModel
import pl.patrykzygo.todo.domain.Task
import pl.patrykzygo.todo.domain.toDatabaseEntity

open class RoomRepositoryImpl(private val db: TaskDatabase): TaskRepository {


    override suspend fun insertTask(vararg tasks: Task) {
        withContext(Dispatchers.IO){
           db.taskDatabaseDao.insert(
               *tasks.map{
                it.toDatabaseEntity()
            }.toTypedArray())
        }
    }

    override suspend fun receiveAllTasks(): LiveData<List<Task>> {
        return withContext(Dispatchers.IO) {
            Transformations.map(db.taskDatabaseDao.getAll()) { list ->
                list.map {
                    it.asDomainModel()
                }
            }
        }
    }

    override suspend fun getTaskWithId(id: Long): LiveData<Task> {
        return withContext(Dispatchers.IO){
            Transformations.map(db.taskDatabaseDao.getWithId(id)){
                it.asDomainModel()
            }
        }
    }

    override suspend fun deleteSpecificTasks(vararg tasks: Task) {
        withContext(Dispatchers.IO){
            db.taskDatabaseDao.deleteTasks(
                *tasks.map {
                    it.toDatabaseEntity()
                }.toTypedArray()
            )
        }
    }

    override suspend fun clearAllTasks() {
        withContext(Dispatchers.IO){
            db.taskDatabaseDao.deleteAllTasks()
        }
    }
}