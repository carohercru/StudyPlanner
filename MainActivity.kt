package com.example.studyplanner


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.studyplanner.Task
import com.example.studyplanner.TaskAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import androidx.recyclerview.widget.RecyclerView
import android.content.Intent
class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var fab: FloatingActionButton
    private lateinit var adapter: TaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerViewTasks)
        fab = findViewById(R.id.fabAddTask)

        // Lista de ejemplo (luego vendrá de Firebase)
        val sampleTasks = listOf(
            Task("Llamar a proveedor", "Confirmar entrega de insumos"),
            Task("Reunión de equipo", "Preparar presentación semanal")
        )

        adapter = TaskAdapter(sampleTasks)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        // Navegación al crear tarea (placeholder)
        fab.setOnClickListener {
            val intent = Intent(this, CreateTaskActivity::class.java)
            startActivity(intent)
        }
    }
}