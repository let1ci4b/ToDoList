package com.example.composetodolist.datasource

import com.example.composetodolist.model.Task
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class DataSource() {

    private val db = FirebaseFirestore.getInstance()

    private val _allTasks = MutableStateFlow<MutableList<Task>>(mutableListOf())
    private val allTasks: StateFlow<MutableList<Task>> = _allTasks

    fun saveTask(task: String, description: String, priorityLevel: Int){
        val taskMap = hashMapOf(
            "task" to task,
            "description" to description,
            "priority level" to priorityLevel
        )
        db.collection("tasks").document(task).set(taskMap).addOnCompleteListener {

        }.addOnFailureListener {

        }
    }

    fun rescueTask() : Flow<MutableList<Task>> {

        val todoList: MutableList<Task> = mutableListOf()

        db.collection("tasks").get().addOnCompleteListener {  querySnapshot ->
            if(querySnapshot.isSuccessful){
                for(document in querySnapshot.result){
                    val task = document.toObject(Task::class.java)
                    todoList.add(task)
                    _allTasks.value = todoList
                }
            }
        }
        return allTasks
    }

    /// TODO review deletion through task name (implement id or warning about override task when title is duplicated)
    fun deleteTask(task: String){
        db.collection("tasks").document(task).delete().addOnCompleteListener { }
    }
}