package com.example.composetodolist.repository

import com.example.composetodolist.datasource.DataSource

class RepositoryTasks() {

    private val dataSource = DataSource()

    fun saveTask(task: String, description: String, priorityLevel: Int){
        dataSource.saveTask(task, description, priorityLevel)
    }
}