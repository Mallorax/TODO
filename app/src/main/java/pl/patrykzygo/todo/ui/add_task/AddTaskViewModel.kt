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

    fun setDate(date: Calendar){
        _date.value = date
    }



    override fun onCleared() {
        super.onCleared()
    }
}