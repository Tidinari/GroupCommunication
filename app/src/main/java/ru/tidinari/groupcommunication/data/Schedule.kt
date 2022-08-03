package ru.tidinari.groupcommunication.data

import kotlinx.serialization.Serializable

@Serializable
data class Schedule(private val schedule: Map<Int, WeekSchedule>) {
    fun onWeek(weekNum: Int): WeekSchedule {
        if (weekNum in 1..18) {
            return schedule[weekNum]!!
        } else {
            throw java.lang.IllegalArgumentException("weekNum should be in range of 1..18")
        }
    }
}

@Serializable
data class WeekSchedule(private val schedule: Map<DayOfWeek, DaySchedule>) {
    fun onDay(dayOfWeek: DayOfWeek): DaySchedule {
        return schedule[dayOfWeek] ?: DaySchedule(mutableListOf())
    }
}

@Serializable
data class DaySchedule(private val _schedule: MutableList<Lesson>) {
    val schedule: List<Lesson> = _schedule
    val size: Int = schedule.size

    fun add(lesson: Lesson) {
        _schedule.add(lesson);
    }

    operator fun get(pos: Int): Lesson {
        return schedule[pos]
    }
}