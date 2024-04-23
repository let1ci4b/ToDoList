package com.example.composetodolist.datasource

import com.google.firebase.firestore.FirebaseFirestore

class DataSource() {

    private val db = FirebaseFirestore.getInstance()
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
}