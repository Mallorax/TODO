package pl.patrykzygo.todo.ui.add_task

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.*
import pl.patrykzygo.todo.R
import pl.patrykzygo.todo.database.TaskDatabase
import pl.patrykzygo.todo.domain.Task
import pl.patrykzygo.todo.repository.RoomRepositoryImpl
import pl.patrykzygo.todo.repository.TaskRepository
import java.lang.NullPointerException
import java.text.SimpleDateFormat
import java.util.*

class AddTaskViewModel(application: Application) : AndroidViewModel(application) {

    private val database = TaskDatabase.getInstance(application)
    private val tasksRepo: TaskRepository = RoomRepositoryImpl(database.taskDatabaseDao)


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

    fun formatIntoCalendar(date: String, time: String): Calendar? {
        var c = Calendar.getInstance()
        return if (date != "" && time != ""){
            val cString = "$date $time"
            c.time = SimpleDateFormat("dd/MM/yyyy HH:mm").parse(cString)
            c
        }else if (date != "" && time == ""){
            c.time = SimpleDateFormat("dd/MM/yyyy").parse(date)
            c.set(Calendar.MINUTE, 1)
            c
        }else if (date == "" && time != ""){
            c.set(Calendar.YEAR, 1970)
            c.time = SimpleDateFormat("HH:mm").parse(time)
            c
        }else {
            null
        }
    }

    fun validateDateInput(selectedDate: Calendar?):Boolean{
        if (selectedDate == null) return true
        val currentDate = Calendar.getInstance()
        return !selectedDate.before(currentDate)
    }


    override fun onCleared() {
        super.onCleared()
    }

    //TODO: Should be done even if viewModel gets destroyed, so move it into lower layer
    fun insertNewTask(task: Task) {
        GlobalScope.launch {
            tasksRepo.insertTask(task)
        }
    }
}