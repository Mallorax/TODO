package pl.patrykzygo.todo.ui.add_task

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import java.util.*

class AddTaskViewModel: ViewModel() {


    private val _date = MutableLiveData<Calendar>()
    val date: LiveData<Calendar>
    get() = _date

    private val _time = MutableLiveData<Calendar>()
    val time: LiveData<Calendar>
    get() = _time

    fun setTime(time: Calendar){
        _time.value = time
    }

    fun setDate(date: Calendar){
        _date.value = date
    }



    override fun onCleared() {
        super.onCleared()
    }
}