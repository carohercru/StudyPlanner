package com.example.studyplanner.model

data class Task(
    var id: String = "",
    var title: String = "",
    var description: String = "",
    var userId: String = "",
    var timestamp: Long = System.currentTimeMillis()
)
