package com.example.todolistapp.presentation.tasks

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolistapp.R
import com.example.todolistapp.data.models.task.Task
import com.example.todolistapp.presentation.add_task.AddTaskDialog
import com.example.todolistapp.presentation.add_task.AddTaskListener
import com.example.todolistapp.presentation.tasks.adapters.TasksAdapter
import com.example.todolistapp.utils.BaseFragment
import com.example.todolistapp.utils.PresentersStorage
import kotlinx.android.synthetic.main.fragment_tasks.*
import kotlinx.android.synthetic.main.fragment_tasks.view.*

class TasksFragment : BaseFragment(), AddTaskListener, TasksView {

    companion object {
        fun newInstance(): TasksFragment {
            return TasksFragment()
        }
    }

    private lateinit var tasksAdapter: TasksAdapter
    private lateinit var presenter: TasksPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tasks, container, false)
    }

    override fun attachPresenter() {
        val presenter = PresentersStorage.getPresenter(viewId)
        if (presenter !is TasksPresenter) {
            this.presenter = TasksPresenter()
            return
        }
        this.presenter = presenter
    }

    override fun getPresenter(): TasksPresenter {
        return presenter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar.inflateMenu(R.menu.menu_tasks_fragment)

        toolbar.setOnMenuItemClickListener {
            onOptionsItemSelected(it)
        }

        tasksAdapter = TasksAdapter()
        val recyclerView = view.recyclerViewTasks
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = tasksAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        addTask_btn.setOnClickListener {
            AddTaskDialog.show(childFragmentManager)
        }
    }

    override fun onStart() {
        super.onStart()
        presenter.bindView(this)
    }

    override fun onStop() {
        super.onStop()
        presenter.unbindView()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_tasks_fragment, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.delete -> {
                presenter.deleteUser()
                true
            }

            R.id.exit -> {
                presenter.logout()
                true
            }

            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    override fun onTaskAdded(task: Task) {
        presenter.onTaskAdded(task)
    }

    override fun updateTaskList(tasks: List<Task>) {
        val listCopy = tasks.toMutableList().map { it.copy() }
        tasksAdapter.submitList(listCopy)
    }

    override fun updateState(tasksScreenStates: TasksScreenStates) {
        recyclerViewTasks.visibility = View.GONE
        addTask_btn.visibility = View.GONE
        loadProgressBar.visibility = View.GONE
        when (tasksScreenStates) {
            TasksScreenStates.CONTENT -> {
                recyclerViewTasks.visibility = View.VISIBLE
                addTask_btn.visibility = View.VISIBLE
            }
            TasksScreenStates.LOADING -> {
                loadProgressBar.visibility = View.VISIBLE
            }
            TasksScreenStates.ERROR -> {
            }
            TasksScreenStates.START -> {
            }
        }
    }
}


