package pl.patrykzygo.todo.ui

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import pl.patrykzygo.todo.R
import pl.patrykzygo.todo.databinding.TasksListFragmentBinding
import pl.patrykzygo.todo.viewmodels.TaskViewModel

class TasksListFragment : Fragment(){

    private val viewModel: TaskViewModel by lazy {
        ViewModelProviders.of(this).get(TaskViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        val binding = TasksListFragmentBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        val adapter = TasksListAdapter(TasksListAdapter.OnClickListener {
            Toast.makeText(this.context, it.taskId.toString(), Toast.LENGTH_LONG).show()
        })
        binding.tasksRecycler.adapter = adapter

        return binding.root
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, view!!.findNavController())
                || super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_main, menu)
    }
}