package com.example.composetodolist.repository

import com.example.composetodolist.datasource.DataSource
import com.example.composetodolist.model.Task
import kotlinx.coroutines.flow.Flow

class RepositoryTasks() {

    private val dataSource = DataSource()

    fun saveTask(task: String, description: String, priorityLevel: Int){
        dataSource.saveTask(task, description, priorityLevel)
    }

    fun rescueTasks() : Flow<MutableList<Task>> {
        return dataSource.rescueTask()
    }

    fun deleteTask(task: String){
        dataSource.deleteTask(task)
    }

    /// TODO edit task
}