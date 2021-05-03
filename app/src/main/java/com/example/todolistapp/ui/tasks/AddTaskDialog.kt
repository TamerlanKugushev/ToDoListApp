package com.example.todolistapp.ui.tasks

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import com.example.todolistapp.R
import com.example.todolistapp.data.models.task.TaskRequest
import com.example.todolistapp.data.models.task.TaskResponse
import com.example.todolistapp.data.repositories.Repository
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_add.*

class AddTaskDialog : BottomSheetDialogFragment(){


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
    ): View? {
        return inflater.inflate(R.layout.fragment_add, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        add_btn.setOnClickListener {
            addTask()
        }
    }

    override fun onDetach() {
        super.onDetach()
        addTaskListener = null
    }


    @SuppressLint("CheckResult")
    private fun addTask() {
        val descriptionOfTask = addEditTextDescriptionOfTask.text.toString()
        Repository
            .addTask(descriptionOfTask)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {
                    //Repository.taskList.add()
                    //addTaskListener?.onTaskAdded(it)
                    val taskResponse = it
                    addTaskListener?.onTaskAdded(it)
                    Log.i("ADD", it.toString())
                    dismiss()
                },
                onError = {
                    Log.i("ADD", it.toString())
                }
            )
    }

}

interface AddTaskListener {
    fun onTaskAdded(task: TaskResponse)
}

