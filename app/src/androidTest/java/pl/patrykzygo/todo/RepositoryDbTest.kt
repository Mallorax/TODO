package pl.patrykzygo.todo

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import io.mockk.*
import io.mockk.impl.annotations.SpyK
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import pl.patrykzygo.todo.database.TaskDatabase
import pl.patrykzygo.todo.database.TaskDatabaseDao
import pl.patrykzygo.todo.database.asDomainModel
import pl.patrykzygo.todo.repository.RoomRepositoryImpl
import pl.patrykzygo.todo.repository.TaskRepository


@RunWith(JUnit4::class)
class RepositoryDbTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    lateinit var repo: RoomRepositoryImpl

    private val testData = createRandomTasks(5)
    lateinit var db: TaskDatabase


    @Before
    fun init() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, TaskDatabase::class.java
        ).allowMainThreadQueries().build()
        repo = RoomRepositoryImpl(db)
    }

    @After
    fun close() {
        db.close()
    }


    @Test
    fun receiveAllTasksTest() {
        db.taskDatabaseDao.insert(*testData.toTypedArray())
        val result = runBlocking {
            repo.receiveAllTasks()
        }.blockingObserve()
        assertEquals(result!![0].title, testData[0].title)

    }

    @Test
    fun insertTaskTest() {
        val sp = spyk(repo)
        val test = testData.map {
            it.asDomainModel()
        }.toTypedArray()
        runBlocking {
            sp.insertTask(*test)
        }
        coVerify{
            sp.insertTask(*test)
        }


    }
}