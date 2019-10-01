package pl.patrykzygo.todo.database

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE

@Dao
interface TaskDatabaseDao {

    @Insert(onConflict = REPLACE)
    fun insert(vararg task: Task)

    @Query("SELECT * FROM tasks_table")
    fun getAll(): LiveData<List<Task>>

    @Query("SELECT * FROM tasks_table WHERE id = :id")
    fun getWithId(id: Long): LiveData<Task>

    @Delete
    fun deleteTasks(vararg tasks: Task)

}