package pl.patrykzygo.todo.ui

import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import pl.patrykzygo.todo.domain.Task
import pl.patrykzygo.todo.ui.task_list.TasksListAdapter
import java.text.SimpleDateFormat
import java.util.*

@BindingAdapter("taskList")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Task>?){
    if (recyclerView.adapter is TasksListAdapter){
        (recyclerView.adapter as TasksListAdapter).submitList(data)
    }
}

@BindingAdapter("taskDate")
fun bindTaskDate(textView: TextView, calendar: Calendar){
    val dateFormat = SimpleDateFormat("dd/MM/yyyy")
    val dateString = dateFormat.format(calendar.time)
    textView.text = dateString
}

@BindingAdapter("taskTime")
fun bindTaskTime(textView: TextView, calendar: Calendar){
    val dateFormat = SimpleDateFormat("HH:mm")
    val timeString = dateFormat.format(calendar.time)
    textView.text = timeString
}

@BindingAdapter("taskPriority")
fun bindTaskPriority(textView: TextView, task: Task){
    textView.text = task.priority.toString()
}
