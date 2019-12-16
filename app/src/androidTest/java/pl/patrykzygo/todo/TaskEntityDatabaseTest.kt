package pl.patrykzygo.todo

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

import pl.patrykzygo.todo.database.TaskDatabase
import pl.patrykzygo.todo.database.TaskDatabaseDao
import java.io.IOException


@RunWith(AndroidJUnit4::class)
class TaskEntityDatabaseTest {


    @get:Rule val rule = InstantTaskExecutorRule()

    private lateinit var taskDao: TaskDatabaseDao
    private lateinit var db: TaskDatabase


    @Before
    fun createDb(){
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, TaskDatabase::class.java).allowMainThreadQueries().build()
        taskDao = db.taskDatabaseDao
    }

    @After
    @Throws(IOException::class)
    fun closeDb(){
        db.close()
    }



    @Test
    @Throws(Exception::class)
    fun insertTaskTest(){
        val task = createRandomTasks(1)[0]
        taskDao.insert(task)
        val result = taskDao.getAll().blockingObserve()
        assert(result!!.isNotEmpty())
    }

    @Test
    @Throws(Exception::class)
    fun getTasksTest(){
        val tasks = createRandomTasks(20)
        taskDao.insert(*tasks.toTypedArray())
        val retrievedTasks = taskDao.getAll().blockingObserve()
        assert(retrievedTasks == tasks.sortedWith(compareBy({it.taskId}, {it.taskId})))
    }

    @Test
    @Throws(Exception::class)
    fun deleteAllTest(){
        val tasks = createRandomTasks(5)
        taskDao.insert(*tasks.toTypedArray())
        taskDao.deleteAllTasks()
        val result = taskDao.getAll().blockingObserve()
        assert(result!!.isEmpty())
    }

    @Test
    @Throws(Exception::class)
    fun deleteSpecificTest(){
        val tasks = createRandomTasks(5)
        taskDao.insert(*tasks.toTypedArray())
        val tasksToDelete = listOf(tasks[(0..4).random()], tasks[(0..4).random()])
        taskDao.deleteTasks(*tasksToDelete.toTypedArray())
        val result = taskDao.getAll().blockingObserve()
        assert(tasksToDelete[0] !in result!! && tasksToDelete[1] !in result!!)
    }

    @Test
    @Throws(Exception::class)
    fun getWithIdTest(){
        val tasks = createRandomTasks(5)
        taskDao.insert(*tasks.toTypedArray())
        val randomId = (0..4).random().toLong()
        val result = taskDao.getWithId(randomId).blockingObserve()
        assertEquals(randomId, result?.taskId)


    }



}