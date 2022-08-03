package ru.tidinari.groupcommunication.domain.models

import kotlinx.serialization.Serializable
import okhttp3.internal.toImmutableList
import okhttp3.internal.toImmutableMap
import ru.tidinari.groupcommunication.data.*

@Serializable
data class DomainSchedule(val schedule: Map<Int, List<DomainLesson>>) {
    fun toData(): Schedule {
        val dataSchedule = schedule.mapValues { domainSchedule ->
            val weekLessons = domainSchedule.value
            val weekSchedule = mutableMapOf<DayOfWeek, DaySchedule>()

            for (domainLesson in weekLessons) {
                val lesson = domainLesson.toData()
                val day = DayOfWeek.of(domainLesson.day)
                if (weekSchedule.containsKey(day)) {
                    weekSchedule[day]!!.add(lesson)
                } else {
                    weekSchedule[day] = DaySchedule(mutableListOf(lesson))
                }
            }

            WeekSchedule(weekSchedule)
        }
        return Schedule(dataSchedule)
    }
}