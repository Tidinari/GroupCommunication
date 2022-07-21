package ru.tidinari.groupcommunication.models.groups.repo.schedule

import kotlinx.serialization.Serializable

@Serializable
data class Lesson(
    val name: String,
    val day: Int,
    val lessonInDay: Int,
    val activityType: String,
    val teacher: String,
    val room: String,
)