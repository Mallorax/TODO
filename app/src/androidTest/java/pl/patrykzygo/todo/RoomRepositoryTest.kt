package pl.patrykzygo.todo

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito.mock
import pl.patrykzygo.todo.database.TaskDatabase
import pl.patrykzygo.todo.database.TaskDatabaseDao
import pl.patrykzygo.todo.repository.RoomRepositoryImpl
import pl.patrykzygo.todo.repository.TaskRepository


@RunWith(JUnit4::class)
class RoomRepositoryTest{

    @get:Rule val rule = InstantTaskExecutorRule ()
    @Mock private lateinit var mockDao: TaskDatabaseDao
    @Mock private lateinit var mockDb: TaskDatabase
    private lateinit var  repo: TaskRepository

    @Before
    fun setup(){
        mockDao = mockDb.taskDatabaseDao
        repo = RoomRepositoryImpl(mockDb)
    }
    



}