package ru.tidinari.groupcommunication.data

import kotlinx.serialization.Serializable

// Represent day of the week
@Serializable
enum class DayOfWeek {
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY,
    SUNDAY;

    companion object {
        fun of(dayOfTheWeekNum: Int): DayOfWeek {
            return values()[dayOfTheWeekNum]
        }
    }
}