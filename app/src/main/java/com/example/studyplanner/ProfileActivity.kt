package com.example.studyplanner

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class ProfileActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var userEmailTextView: TextView
    private lateinit var logoutButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        // Inicializa Firebase Auth
        auth = FirebaseAuth.getInstance()

        // Vincula las vistas del layout
        userEmailTextView = findViewById(R.id.userEmailTextView)
        logoutButton = findViewById(R.id.logoutButton)

        // Muestra el email del usuario actual
        val user = FirebaseAuth.getInstance().currentUser
        val userInfoTextView = findViewById<TextView>(R.id.userInfoTextView)
        val userEmailTextView = findViewById<TextView>(R.id.userEmailTextView)

        userInfoTextView.text = "Información general\nNombre: ${user?.displayName ?: "Sin nombre"}\nCorreo: ${user?.email ?: "Desconocido"}"
        userEmailTextView.text = "Correo: ${user?.email ?: "Desconocido"}"

        // Cerrar sesión y volver al login
        logoutButton.setOnClickListener {
            auth.signOut()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
