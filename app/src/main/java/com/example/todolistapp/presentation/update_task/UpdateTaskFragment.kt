package com.example.todolistapp.presentation.update_task

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.setFragmentResult
import com.example.todolistapp.R
import com.example.todolistapp.Screens
import com.example.todolistapp.presentation.tasks.TasksPresenter
import com.example.todolistapp.utils.BaseFragment
import com.example.todolistapp.utils.PresentersStorage
import kotlinx.android.synthetic.main.fragment_update_task.*


class UpdateTaskFragment : BaseFragment(), UpdateTaskView {

    companion object {
        fun newInstance(id: String): UpdateTaskFragment {
            return UpdateTaskFragment().apply {
                arguments = bundleOf("id" to id)
            }
        }
    }

    private lateinit var presenter: UpdateTaskPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_update_task, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateButton.setOnClickListener {
            presenter.updateTask(description = updateDescriptionOfTask.text.toString())
        }
    }

    override fun attachPresenter() {
        val presenter = PresentersStorage.getPresenter(viewId)
        if (presenter !is UpdateTaskPresenter) {
            this.presenter = UpdateTaskPresenter(requireArguments().getString("id").toString())
            return
        }
        this.presenter = presenter
    }

    override fun getPresenter(): UpdateTaskPresenter {
        return presenter
    }

    override fun onStart() {
        super.onStart()
        presenter.bindView(this)
    }

    override fun onStop() {
        super.onStop()
        presenter.unbindView()
    }

    override fun onTaskUpdated(id: String, description: String) {
        setFragmentResult("requestKey", bundleOf("id" to id, "description" to description))
    }
}