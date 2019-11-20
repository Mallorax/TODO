package pl.patrykzygo.todo.ui.add_task

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.*
import pl.patrykzygo.todo.database.TaskDatabase
import pl.patrykzygo.todo.domain.Task
import pl.patrykzygo.todo.repository.RoomRepositoryImpl
import pl.patrykzygo.todo.repository.TaskRepository
import java.lang.NullPointerException
import java.util.*

class AddTaskViewModel(application: Application) : AndroidViewModel(application) {

    private val database = TaskDatabase.getInstance(application)
    private val tasksRepo: TaskRepository = RoomRepositoryImpl(database)


    private val _date = MutableLiveData<Calendar>()
    val date: LiveData<Calendar>
        get() = _date

    private val _time = MutableLiveData<Calendar>()
    val time: LiveData<Calendar>
        get() = _time

    fun setTime(time: Calendar) {
        _time.value = time
    }

    fun setDate(date: Calendar) {
        _date.value = date
    }


    override fun onCleared() {
        super.onCleared()
    }

    fun insertNewTask(task: Task) {
        GlobalScope.launch(Dispatchers.IO) {
            tasksRepo.insertTask(task)
        }
    }
}