package pl.patrykzygo.todo.ui.task_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import pl.patrykzygo.todo.databinding.TaskDetailsFragmentBinding

class TaskDetailsFragment: Fragment() {

    private val args: TaskDetailsFragmentArgs by navArgs()

    private lateinit var viewModelFactory: TaskDetailsViewModelFactory
    private lateinit var viewModel: TaskDetailsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        val binding = TaskDetailsFragmentBinding.inflate(inflater)

        viewModelFactory = TaskDetailsViewModelFactory(activity!!.application, args.taskId)
        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(TaskDetailsViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        return binding.root
    }
}