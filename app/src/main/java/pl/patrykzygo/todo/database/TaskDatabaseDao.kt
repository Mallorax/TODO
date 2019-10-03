package pl.patrykzygo.todo.database

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE

@Dao
interface TaskDatabaseDao {

    @Insert(onConflict = REPLACE)
    fun insert(vararg taskEntity: TaskEntity)

    @Query("SELECT * FROM tasks_table")
    fun getAll(): LiveData<List<TaskEntity>>

    @Query("SELECT * FROM tasks_table WHERE id = :id")
    fun getWithId(id: Long): LiveData<TaskEntity>

    @Delete
    fun deleteTasks(vararg taskEntities: TaskEntity)

    @Query("DELETE FROM tasks_table")
    fun deleteAllTasks()

}