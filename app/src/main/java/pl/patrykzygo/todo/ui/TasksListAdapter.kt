package pl.patrykzygo.todo.ui

import android.content.DialogInterface
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import pl.patrykzygo.todo.databinding.TasksListFragmentBinding
import pl.patrykzygo.todo.domain.Task

class TasksListAdapter(private val onClickListener: View.OnClickListener): ListAdapter<Task, TasksListAdapter.TaskViewHolder>(DiffCallback) {




    class TaskViewHolder(private var binding: TasksListFragmentBinding)
        :RecyclerView.ViewHolder(binding.root){
        fun bind(task: Task){
            binding.bindedTask = task
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback: DiffUtil.ItemCallback<Task>(){
        override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
