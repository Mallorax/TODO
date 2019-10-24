package pl.patrykzygo.todo.ui

import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import pl.patrykzygo.todo.domain.Task
import java.text.SimpleDateFormat
import java.util.*

@BindingAdapter("taskList")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Task>?){
    val adapter = recyclerView.adapter as TasksListAdapter
    adapter.submitList(data)
}

@BindingAdapter("task_date")
fun bindTaskDate(textView: TextView, date: Date){
    val format = SimpleDateFormat("dd.MM.yyyy HH:mm")
    val dateString = format.format(date)
    textView.text = dateString
}
