package ru.tidinari.groupcommunication

import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import ru.tidinari.groupcommunication.data.models.*
import ru.tidinari.groupcommunication.domain.repo.ScheduleRepository

class TestFacultyScheduleRepo {

    private lateinit var repo: ScheduleRepository

    @Before
    fun initRepository() {
        repo = TestScheduleRepository()
    }

    @Test
    fun testGetAllGroups() = runBlocking {
        val groups = repo.getGroups()

        assertEquals(true, groups.isSuccess)
        assertEquals(setOf(Group("ТЕСТ-00-00")), groups.getOrThrow())
    }

    @Test
    fun testGetSchedule() = runBlocking {
        val schedule = repo.getSchedule()

        val lesson = Lesson(
            TimeIdentifier(
                Week(1),
                DayOfWeek.MONDAY,
                LessonInDay.FIRST
            ), "testName", LessonActivityType.LECTURE, "testTeacher", "testRoom"
        )

        assertEquals(true, schedule.isSuccess)
        assertEquals(FacultySchedule(mapOf(Group("ТЕСТ-00-00") to listOf(lesson))), schedule.getOrThrow())

        val testLesson = repo.getSchedule(Group("ТЕСТ-00-00")).getOrThrow().firstOrNull {
            it.isOn(
                day = DayOfWeek.MONDAY,
                week = Week(1),
                lessonInDay = LessonInDay.FIRST
            )
        }

        assertEquals(lesson, testLesson)
    }
}

class TestScheduleRepository() : ScheduleRepository {

    private val schedule = FacultySchedule(
        mapOf(
            Group("ТЕСТ-00-00") to listOf(
                Lesson(
                    TimeIdentifier(
                        Week(1),
                        DayOfWeek.MONDAY,
                        LessonInDay.FIRST
                    ), "testName", LessonActivityType.LECTURE, "testTeacher", "testRoom"
                )
            )
        )
    )

    override suspend fun getGroups(): Result<Set<Group>> {
        return Result.runCatching { schedule.getGroups() }
    }

    override suspend fun getSchedule(): Result<FacultySchedule> {
        return Result.runCatching { schedule }
    }

    override suspend fun getSchedule(group: Group): Result<List<Lesson>> {
        return Result.runCatching { schedule.getSchedule(group) ?: throw IllegalArgumentException("Unknown group $group") }
    }
}