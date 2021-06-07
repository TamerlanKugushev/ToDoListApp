package com.example.todolistapp.presentation.add_task

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import com.example.todolistapp.R
import com.example.todolistapp.data.models.task.Task
import com.example.todolistapp.utils.BaseBottomSheetDialogFragment
import com.example.todolistapp.utils.PresentersStorage
import kotlinx.android.synthetic.main.fragment_add.*

class AddTaskDialog : BaseBottomSheetDialogFragment(), AddTaskView {

    companion object {

        private const val TAG = "AddTaskFragment"

        fun show(fragmentManager: FragmentManager) {
            val dialog = newInstance()
            dialog.show(fragmentManager, TAG)
        }

        private fun newInstance(): AddTaskDialog {
            return AddTaskDialog()
        }
    }

    private lateinit var presenter: AddTaskPresenter
    private var addTaskListener: AddTaskListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val parentFragment = parentFragment
        if (parentFragment is AddTaskListener) {
            addTaskListener = parentFragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_add, container, false)
    }

    override fun attachPresenter() {
        val presenter = PresentersStorage.getPresenter(viewId)
        if (presenter !is AddTaskPresenter) {
            this.presenter = AddTaskPresenter()
            return
        }
        this.presenter = presenter
    }

    override fun getPresenter(): AddTaskPresenter {
        return presenter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addButton.setOnClickListener {
            addTask()
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

    override fun onDetach() {
        super.onDetach()
        addTaskListener = null
    }

    override fun addTask(task: Task) {
        addTaskListener?.onTaskAdded(task)
        dismiss()
    }

    @SuppressLint("CheckResult")
    private fun addTask() {
        val descriptionOfTask = descriptionOfTask.text.toString()
        presenter.addTask(descriptionOfTask)
    }

}

interface AddTaskListener {
    fun onTaskAdded(task: Task)
}

