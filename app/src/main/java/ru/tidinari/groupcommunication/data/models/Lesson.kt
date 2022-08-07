package ru.tidinari.groupcommunication.data.models

import kotlinx.serialization.Serializable

@Serializable
data class Lesson(
    val timeIdentifier: TimeIdentifier,
    val name: String,
    val activityType: LessonActivityType,
    val teacher: String,
    val room: String
) {
    fun isOn(
        week: Week = Week.anyWeek,
        day: DayOfWeek = DayOfWeek.ANY,
        lessonInDay: LessonInDay = LessonInDay.ANY
    ): Boolean {
        return timeIdentifier.isOn(week, day, lessonInDay)
    }
}

@Serializable
data class TimeIdentifier(
    val _week: Week,
    val _day: DayOfWeek,
    val _lessonInDay: LessonInDay
) {
    init {
        if (_week == Week.anyWeek || _day == DayOfWeek.ANY || _lessonInDay == LessonInDay.ANY) {
            throw IllegalArgumentException("ANY not allowed in constructor")
        }
    }

    fun isOn(
        week: Week = Week.anyWeek,
        day: DayOfWeek = DayOfWeek.ANY,
        lessonInDay: LessonInDay = LessonInDay.ANY
    ): Boolean {
        val weekCon = if (week == Week.anyWeek) true else week == _week
        val dayCon = if (day == DayOfWeek.ANY) true else day == _day
        val lessonInDayCon = if (lessonInDay == LessonInDay.ANY) true else lessonInDay == _lessonInDay
        return weekCon && dayCon && lessonInDayCon
    }
}
