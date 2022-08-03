package ru.tidinari.groupcommunication

import org.junit.Before
import org.junit.Test
import org.junit.Assert.*
import ru.tidinari.groupcommunication.data.*

class DefaultHashProviderImplTest {

    lateinit var schedule: Schedule

    @Before
    fun setUpImplementation() {
        schedule = Schedule(
            mapOf(
                1 to WeekSchedule(
                    mapOf(
                        DayOfWeek.SUNDAY to DaySchedule(
                            mutableListOf(
                                Lesson(
                                    "Тест",
                                    DayOfWeek.SUNDAY,
                                    LessonInDay.FIRST,
                                    LessonActivityType.LECTURE,
                                    "Павлович А. А.",
                                    "А-422"
                                )
                            )
                        )
                    )
                )
            )
        )
    }

    @Test
    fun testCorrectHashOutput() {

    }
}