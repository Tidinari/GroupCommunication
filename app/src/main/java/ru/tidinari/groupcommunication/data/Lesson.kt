package ru.tidinari.groupcommunication.data

import kotlinx.serialization.Serializable

@Serializable
data class Lesson(
    val name: String,
    val lessonInDay: LessonInDay,
    val activityType: LessonActivityType,
    val teacher: String,
    val room: String,
)
