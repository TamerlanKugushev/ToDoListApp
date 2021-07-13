package com.example.todolistapp.presentation.tasks.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.todolistapp.R
import com.example.todolistapp.data.models.task.Task
import kotlinx.android.synthetic.main.item_task.view.*

class TasksAdapter(private val onItemClick: (id: String) -> Unit) :
    ListAdapter<Task, TasksAdapter.TaskViewHolder>(TasksDiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false)
        return TaskViewHolder(view, onItemClick)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    class TaskViewHolder(
        itemView: View,
        onItemClick: (id: String) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {

        private var id: String = ""

        init {
            itemView.setOnClickListener {
                onItemClick.invoke(id)
            }
        }

        fun bind(task: Task) {
            itemView.descriptionOfTask_txt.text = task.description
            id = task.id
        }
    }

    class TasksDiffUtilCallback : DiffUtil.ItemCallback<Task>() {

        override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
            return oldItem == newItem
        }
    }
}


