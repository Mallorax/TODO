package pl.patrykzygo.todo.ui.task_list

        import android.app.AlertDialog
        import android.os.Bundle
                import android.view.*
                import androidx.fragment.app.Fragment
                import androidx.lifecycle.ViewModelProviders
                import androidx.navigation.findNavController
                import androidx.navigation.ui.NavigationUI
                import androidx.recyclerview.widget.DividerItemDecoration
                import com.google.android.material.snackbar.Snackbar
                import pl.patrykzygo.todo.R
                import pl.patrykzygo.todo.databinding.TasksListFragmentBinding

        class TasksListFragment : Fragment(){

            private val listViewModel: TaskListViewModel by lazy {
                ViewModelProviders.of(this).get(TaskListViewModel::class.java)
            }


            override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                                      savedInstanceState: Bundle?): View? {
                setHasOptionsMenu(true)
                val binding = TasksListFragmentBinding.inflate(inflater)
                binding.lifecycleOwner = this
                binding.viewModel = listViewModel
                val adapter =
                    TasksListAdapter(TasksListAdapter.OnClickListener { task, view ->
                        if (task != null) {
                            view.findNavController().navigate(TasksListFragmentDirections.actionTasksListFragmentToTaskDetailsFragment(task.taskId))
                        }else{
                            Snackbar.make(view, "There is no task to go to", Snackbar.LENGTH_LONG).show()
                        }
                    })
                binding.tasksRecycler.adapter = adapter
                binding.tasksRecycler.addItemDecoration(DividerItemDecoration(binding.tasksRecycler.context, DividerItemDecoration.VERTICAL))
                binding.addTaskActionButton.setOnClickListener { view ->
                    view.findNavController().navigate(TasksListFragmentDirections.actionTasksListFragmentToAddTaskFragment())
                }

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