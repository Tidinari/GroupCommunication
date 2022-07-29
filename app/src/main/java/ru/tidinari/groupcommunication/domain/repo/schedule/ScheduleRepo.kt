package ru.tidinari.groupcommunication.domain.repo.schedule

import retrofit2.http.GET
import retrofit2.http.Query

interface ScheduleRepo {
    @GET("./generated-data")
    suspend fun getGroupSchedule(@Query("group") group: String): Map<Int, List<Lesson>>
}
