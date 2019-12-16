package pl.patrykzygo.todo.database

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import kotlinx.coroutines.flow.Flow
import pl.patrykzygo.todo.domain.Task

@Dao
interface TaskDatabaseDao {

    @Insert(onConflict = REPLACE)
    suspend fun insert(vararg task: TaskEntity)

    @Query("SELECT * FROM tasks_table")
    suspend fun getAll(): List<TaskEntity>

    @Query("SELECT * FROM tasks_table WHERE id = :id")
    suspend fun getWithId(id: Long): TaskEntity

    @Delete
    suspend fun deleteTasks(vararg tasks: TaskEntity)

    @Query("DELETE FROM tasks_table")
    suspend fun clearAllTasks()

}