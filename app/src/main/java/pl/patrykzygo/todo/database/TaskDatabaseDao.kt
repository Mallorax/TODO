package pl.patrykzygo.todo.database

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE

@Dao
interface TaskDatabaseDao {

    @Insert(onConflict = REPLACE)
    suspend fun insert(vararg task: Task)

    @Query("SELECT * FROM tasks_table")
    suspend fun getAll(): LiveData<List<Task>>

    @Query("SELECT * FROM tasks_table WHERE id = :id")
    suspend fun getWithId(id: Long): LiveData<Task>

    @Delete
    suspend fun deleteTasks(vararg tasks: Task)

    @Query("DELETE FROM tasks_table")
    suspend fun clearAllTasks()

}