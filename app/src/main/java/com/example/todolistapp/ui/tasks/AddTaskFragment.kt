package com.example.todolistapp.ui.tasks

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.todolistapp.R
import com.example.todolistapp.Router
import com.example.todolistapp.data.repositories.Repository
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_add.*

class AddTaskFragment : BottomSheetDialogFragment() {


    companion object {
        @JvmStatic
        fun newInstance(): AddTaskFragment {
            return AddTaskFragment()
        }
    }

    private var router: Router? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is Router) {
            router = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        router = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        add_btn.setOnClickListener { }
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
                    Repository.taskList.add(it)
                    router?.navigateToTasksScreen()
                    Log.i("ADD", it.toString())
                },
                onError = {
                    Log.i("ADD", it.toString())
                }
            )
    }
}