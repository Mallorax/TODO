package pl.patrykzygo.todo.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

@Dao
interface TaskDatabaseDao {

    @Insert(onConflict = REPLACE)
    fun insert(task: Task)

    @Query("SELECT * FROM tasks_table")
    fun getAll(): LiveData<List<Task>>

    @Query("SELECT * FROM tasks_table WHERE taskId = :id")
    fun getWithId(id: Long): LiveData<Task>

    @Delete
    fun deleteTasks(vararg tasks: Task)

}