package com.example.todolistapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todolistapp.R
import com.example.todolistapp.data.models.task.TaskResponse
import kotlinx.android.synthetic.main.task_item.view.*

class TasksAdapter : RecyclerView.Adapter<TasksAdapter.TaskViewHolder>() {

    private var taskList = emptyList<TaskResponse>()


    class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.task_item, parent, false)
        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val currentItem = taskList[position]
        holder.itemView.descriptionOfTask_txt.text = currentItem.description
    }

    override fun getItemCount(): Int {
        return taskList.size
    }

    fun setData(taskList: List<TaskResponse>) {
        this.taskList = taskList
    }
}


