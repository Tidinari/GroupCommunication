package ru.tidinari.groupcommunication.models.groups.repo.schedule

import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ScheduleRepo {
    @GET("./generated-data")
    suspend fun getGroupSchedule(group: String): Map<Int, List<Lesson>>
}
