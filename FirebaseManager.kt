FirebaseManager

package com.example.studyplanner

import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class FirebaseManager {

    private val database: FirebaseDatabase = FirebaseDatabase.getInstance()
    private val tasksRef: DatabaseReference = database.getReference("tasks")

    // Guardar tarea en Firebase
    fun saveTask(task: Task, onComplete: (Boolean) -> Unit) {
        val key = tasksRef.push().key // genera un ID único
        if (key == null) {
            onComplete(false)
            return
        }
        task.id = key
        tasksRef.child(key).setValue(task)
            .addOnSuccessListener { onComplete(true) }
            .addOnFailureListener { onComplete(false) }
    }

    // Obtener tareas de un usuario específico
    fun getTasks(userId: String, onResult: (List<Task>) -> Unit) {
        tasksRef.orderByChild("userId").equalTo(userId)
            .get()
            .addOnSuccessListener { snapshot ->
                val list = mutableListOf<Task>()
                for (child in snapshot.children) {
                    val task = child.getValue(Task::class.java)
                    if (task != null) list.add(task)
                }
                onResult(list)
            }
            .addOnFailureListener { onResult(emptyList()) }
    }

    // Escuchar cambios en tiempo real (opcional)
    fun listenTasks(userId: String, onChange: (List<Task>) -> Unit) {
        tasksRef.orderByChild("userId").equalTo(userId)
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val list = mutableListOf<Task>()
                    for (child in snapshot.children) {
                        val task = child.getValue(Task::class.java)
                        if (task != null) list.add(task)
                    }
                    onChange(list)
                }

                override fun onCancelled(error: DatabaseError) {
                    onChange(emptyList())
                }
            })
    }
}
