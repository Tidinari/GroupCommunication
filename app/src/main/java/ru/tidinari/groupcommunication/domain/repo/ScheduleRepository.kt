package ru.tidinari.groupcommunication.domain.repo

import ru.tidinari.groupcommunication.data.models.FacultySchedule
import ru.tidinari.groupcommunication.data.models.Group
import ru.tidinari.groupcommunication.data.models.Lesson

interface ScheduleRepository {
    suspend fun getGroups(): Result<Set<Group>>
    suspend fun getSchedule(): Result<FacultySchedule>
    suspend fun getSchedule(group: Group): Result<List<Lesson>>
}