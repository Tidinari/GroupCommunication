package ru.tidinari.groupcommunication.models.groups.repo.schedule

import retrofit2.http.GET
import retrofit2.http.POST

interface ScheduleRepo {

    @GET("./getschedule")
    suspend fun getGroupSchedule(group: String, hash: String): List<Lesson>

    @POST("./setimportant")
    suspend fun setGroupImportantLesson(group: String, hash: String, user: Short, flag: Short, weekNum: Short, lessonInDay: Short)
}