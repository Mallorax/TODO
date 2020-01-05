package pl.patrykzygo.todo.ui.add_task

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.*
import pl.patrykzygo.todo.R
import pl.patrykzygo.todo.database.TaskDatabase
import pl.patrykzygo.todo.domain.Task
import pl.patrykzygo.todo.domain.Timestamp
import pl.patrykzygo.todo.repository.RoomRepositoryImpl
import pl.patrykzygo.todo.repository.TaskRepository
import java.lang.NullPointerException
import java.lang.StringBuilder
import java.sql.Time
import java.text.SimpleDateFormat
import java.util.*

class AddTaskViewModel(application: Application) : AndroidViewModel(application) {

    private val database = TaskDatabase.getInstance(application)
    private val tasksRepo: TaskRepository = RoomRepositoryImpl(database.taskDatabaseDao)


    private val _cycle = MutableLiveData<String>()
    val cycle: LiveData<String>
        get() = _cycle

    private val _date = MutableLiveData<Calendar>()
    val date: LiveData<Calendar>
        get() = _date

    private val _time = MutableLiveData<Calendar>()
    val time: LiveData<Calendar>
        get() = _time



    fun formatIntoTimestamp(date: String, time: String, cycle: String): Timestamp {
        val c = Calendar.getInstance()
        var isDateSet = false
        var isTimeSet = false
        if(date !="" && time != ""){
            val cString = "$date $time"
            c.time = SimpleDateFormat("dd/MM/yyyy HH:mm").parse(cString)
            isDateSet = true
            isTimeSet = true
        }else if (date != "" && time == ""){
            c.time = SimpleDateFormat("dd/MM/yyyy").parse(date)
            isDateSet = true
            isTimeSet = false
        }else if (date == "" && time != ""){
            c.time = SimpleDateFormat("HH:mm").parse(time)
            isDateSet = false
            isTimeSet = true
        }
        return Timestamp(
            cycle,
            c,
            isDateSet,
            isTimeSet
        )
    }

    fun validateDateInput(selectedDate: Timestamp?):Boolean{
        if (selectedDate == null) return true
        if(!selectedDate.isDateSet) return true
        val currentDate = Calendar.getInstance()
        return (!selectedDate.timestampDate.before(currentDate) || selectedDate.timestampDate == currentDate)
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

    fun setCycle(cycle: String){
        _cycle.value = cycle
    }

    fun setTime(time: Calendar) {
        _time.value = time
    }

    fun setDate(date: Calendar) {
        _date.value = date
    }
}