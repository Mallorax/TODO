package pl.patrykzygo.todo.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import kotlinx.coroutines.flow.Flow
import pl.patrykzygo.todo.domain.Task


@Dao
interface TaskDatabaseDao {

    @Insert(onConflict = REPLACE)
    suspend fun insert(vararg task: TaskEntity)

    @Query("SELECT * FROM tasks_table")
    fun getAll(): LiveData<List<TaskEntity>>

    @Query("SELECT * FROM tasks_table")
    fun getAllPaging(): DataSource.Factory<Int, TaskEntity>

    @Query("SELECT * FROM tasks_table WHERE task_id = :id")
    fun getWithId(id: Long): LiveData<TaskEntity>

    @Delete
    suspend fun deleteTasks(vararg tasks: TaskEntity)

    @Query("DELETE FROM tasks_table")
    suspend fun clearAllTasks()

}