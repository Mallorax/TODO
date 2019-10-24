package pl.patrykzygo.todo.ui

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import pl.patrykzygo.todo.domain.Task

@BindingAdapter("taskList")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Task>?){
    val adapter = recyclerView.adapter as TasksListAdapter
    adapter.submitList(data)
}
