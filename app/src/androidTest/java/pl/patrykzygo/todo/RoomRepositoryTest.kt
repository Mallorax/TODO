package pl.patrykzygo.todo

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations
import pl.patrykzygo.todo.database.TaskDatabase
import pl.patrykzygo.todo.database.TaskDatabaseDao
import pl.patrykzygo.todo.database.TaskEntity
import pl.patrykzygo.todo.repository.RoomRepositoryImpl
import pl.patrykzygo.todo.repository.TaskRepository



@RunWith(JUnit4::class)
class RoomRepositoryTest{

    @get:Rule val rule = InstantTaskExecutorRule ()
    private lateinit var  repo: TaskRepository


    private lateinit var taskDao: TaskDatabaseDao
    private lateinit var db: TaskDatabase


    @Before
    fun createDb(){
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, TaskDatabase::class.java).allowMainThreadQueries().build()
        taskDao = db.taskDatabaseDao
        repo = RoomRepositoryImpl(db)
    }



    @Test
    fun receiveAllTasksTest(){
        val testData = createRandomTasks(5)
        taskDao.insert(*testData.toTypedArray())
        val result = runBlocking {
            repo.receiveAllTasks()
        }.blockingObserve()
        assertEquals(result!![0].title, testData[0].title )

    }



}