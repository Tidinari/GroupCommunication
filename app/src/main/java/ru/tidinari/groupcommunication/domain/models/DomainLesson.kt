package ru.tidinari.groupcommunication.domain.models

import kotlinx.serialization.Serializable
import ru.tidinari.groupcommunication.data.models.Lesson
import ru.tidinari.groupcommunication.data.models.LessonActivityType
import ru.tidinari.groupcommunication.data.models.LessonInDay

@Serializable
data class DomainLesson(
    val name: String,
    val day: Int,
    val lessonInDay: Int,
    val activityType: String,
    val teacher: String,
    val room: String,
) {
    fun toData(): Lesson {
        return Lesson(
            name,
            LessonInDay.of(lessonInDay),
            LessonActivityType.of(activityType),
            teacher,
            room
        )
    }
}