package ru.tidinari.groupcommunication.data.models

import kotlinx.serialization.Serializable

@Serializable
class Week private constructor() {
    private var _weekNum = Int.MAX_VALUE
    val weekNum : Int
        get() = _weekNum

    constructor(week: Int) : this() {
        if (week !in 1..18) {
            throw IllegalArgumentException("$week should be in range 1..18")
        }
        _weekNum = week
    }

    override fun equals(other: Any?): Boolean {
        if (other !is Week) return false
        return other.weekNum == weekNum
    }

    companion object {
        val anyWeek = Week()
    }
}

// Represent time of the lesson
@Serializable
enum class LessonInDay(val value: String) {
    FIRST("09:00\n10:30"),
    SECOND("10:40\n12:10"),
    THIRD("12:40\n14:10"),
    FORTH("14:20\n15:50"),
    FIFTH("16:20\n17:50"),
    SIXTH("18:00\n19:30"),
    SEVENTH("19:40\n21:00"),
    // For filtering
    ANY("");

    companion object {
        fun of(lessonInDayNum: Int): LessonInDay {
            return values()[lessonInDayNum]
        }
    }
}

@Serializable
enum class DayOfWeek {
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY,
    SUNDAY,
    // For filtering
    ANY;

    companion object {
        fun of(dayOfTheWeekNum: Int): DayOfWeek {
            return values()[dayOfTheWeekNum]
        }
    }
}

// Represent activity of the lesson
@Serializable
enum class LessonActivityType(val value: String) {
    PRACTISE("пр"),
    LECTURE("лк"),
    LABORATORY("лаб"),
    UNKNOWN("неизв");

    companion object {
        fun of(activityType: String): LessonActivityType {
            values().forEach {
                if (activityType.lowercase() == it.value)
                    return it
            }
            return UNKNOWN
        }
    }
}