package ru.tidinari.groupcommunication.domain.models

import kotlinx.serialization.Serializable
import ru.tidinari.groupcommunication.data.models.DayOfWeek
import ru.tidinari.groupcommunication.data.models.DaySchedule
import ru.tidinari.groupcommunication.data.models.Schedule
import ru.tidinari.groupcommunication.data.models.WeekSchedule

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