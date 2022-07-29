package ru.tidinari.groupcommunication.domain.repo.schedule

import kotlinx.serialization.Serializable

// TODO: добавить domain директорию, конвертировать данные, полученные из интернета, в data class с этим энамом
@Serializable
data class Lesson(
    val name: String,
    val day: Int,
    val lessonInDay: Int,
    val activityType: String,
    val teacher: String,
    val room: String,
)