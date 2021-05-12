package com.example.todolistapp.presentation.tasks

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolistapp.R
import com.example.todolistapp.Router
import com.example.todolistapp.data.models.task.TaskResponse
import com.example.todolistapp.data.repositories.Repository
import com.example.todolistapp.presentation.tasks.adapters.TasksAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_tasks.*
import kotlinx.android.synthetic.main.fragment_tasks.view.*

class TasksFragment : Fragment(), AddTaskListener {
    companion object {
        fun newInstance(): TasksFragment {
            return TasksFragment()
        }
    }

    private var router: Router? = null

    private lateinit var tasksAdapter: TasksAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tasks, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is Router) {
            router = context
        }
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
        tasksAdapter.setData(Repository.taskList)
        tasksAdapter.notifyDataSetChanged()


        floatingActionButtonAddTask.setOnClickListener {
            AddTaskDialog.show(childFragmentManager)
        }
    }

    override fun onDetach() {
        super.onDetach()
        router = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_tasks_fragment, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.delete -> {
                Repository
                    .deleteUser()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeBy(
                        onSuccess = {
                            Log.i("DELETE", it.toString())
                        },
                        onError = {
                            Log.i("DELETE", it.toString())
                        }
                    )
                true
            }

            R.id.exit -> {
                Repository
                    .logout()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeBy(
                        onSuccess = {
                            Log.i("LOGOUT", it.toString())
                        },
                        onError = {
                            Log.i("LOGOUT", it.toString())
                        }
                    )
                true
            }

            else -> {
                super.onOptionsItemSelected(item)
            }
        }

    }

    override fun onTaskAdded(task: TaskResponse) {
        tasksAdapter.addTask(task)
    }
}

