package ru.tidinari.groupcommunication.models.groups.repo.schedule

import kotlinx.serialization.Serializable

@Serializable
data class Lesson(
    val weeks: Short,
    val lessonInDay: Short,
    val name: String,
    val isImportant: Short
)