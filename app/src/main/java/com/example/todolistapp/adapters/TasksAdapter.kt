package com.example.todolistapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todolistapp.R
import com.example.todolistapp.data.models.task.TaskResponse
import kotlinx.android.synthetic.main.item_task.view.*

class TasksAdapter : RecyclerView.Adapter<TasksAdapter.TaskViewHolder>() {

    private var taskList = emptyList<TaskResponse>()


    class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(task: TaskResponse) {
            itemView.descriptionOfTask_txt.text = task.description
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false)
        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val currentItem = taskList[position]
        holder.bind(currentItem)
    }

    override fun getItemCount(): Int {
        return taskList.size
    }

    fun setData(taskList: List<TaskResponse>) {
        this.taskList = taskList
        notifyDataSetChanged()
    }

    fun addTask(task: TaskResponse) {
        val mutableTaskList = taskList.toMutableList()
        mutableTaskList.add(task)
        notifyDataSetChanged()
    }
}


