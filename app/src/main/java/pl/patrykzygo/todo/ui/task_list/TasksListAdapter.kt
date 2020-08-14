package pl.patrykzygo.todo.ui.task_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import pl.patrykzygo.todo.databinding.TaskListItemBinding
import pl.patrykzygo.todo.domain.Task

class TasksListAdapter(private val onClickListener: OnClickListener,
                       private val onLongClickListener: OnLongClickListener)
    : PagedListAdapter<Task, TasksListAdapter.TaskViewHolder>(DiffCallback) {




    class TaskViewHolder(private var binding: TaskListItemBinding)
        :RecyclerView.ViewHolder(binding.root){
        fun bind(task: Task?){
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
        val inflater = LayoutInflater.from(parent.context)
        val binding = TaskListItemBinding.inflate(inflater, parent, false)
        return TaskViewHolder(binding)
    }



    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = getItem(position)
        holder.itemView.setOnClickListener{
            onClickListener.onClick(task, holder.itemView)
        }
        holder.itemView.setOnLongClickListener{
            onLongClickListener.onLongClick(task, holder.itemView)
        }
        holder.bind(task)
    }

    class OnClickListener(val clickListener: (task: Task?, v: View) -> Unit){
        fun onClick(task: Task?, view: View) = clickListener(task, view)
    }

    class OnLongClickListener(val longClickListener: (Task?, View) -> Boolean){
        fun onLongClick(task: Task?, view: View): Boolean = longClickListener(task, view)
    }


}
