package pl.patrykzygo.todo

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.CoreMatchers.equalTo
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import pl.patrykzygo.todo.database.NotificationType
import pl.patrykzygo.todo.database.Task
import pl.patrykzygo.todo.database.TaskDatabase
import pl.patrykzygo.todo.database.TaskDatabaseDao
import java.io.IOException
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

@RunWith(AndroidJUnit4::class)
class TaskDatabaseTest {


    @get:Rule val rule = InstantTaskExecutorRule()

    private lateinit var taskDao: TaskDatabaseDao
    private lateinit var db: TaskDatabase


    @Before
    fun createDb(){
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, TaskDatabase::class.java).build()
        taskDao = db.taskDatabaseDao
    }

    @After
    @Throws(IOException::class)
    fun closeDb(){
        db.close()
    }



    @Test
    @Throws(Exception::class)
    fun insertTest(){
        val task = Task(0,
            "test",
            "testDesc",
            "14-10-2019",
            false,
            NotificationType.NOTIFICATION_POPUP,
            1,
            "TEST")

        taskDao.insert(task)
        val result = taskDao.getAll().blockingObserve()
        assert(result!!.isNotEmpty())
    }

    private fun <T> LiveData<T>.blockingObserve(): T? {
        var value: T? = null
        val latch = CountDownLatch(1)

        val observer = Observer<T> { t ->
            value = t
            latch.countDown()
        }

        observeForever(observer)

        latch.await(2, TimeUnit.SECONDS)
        return value
    }

}