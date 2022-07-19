package ru.tidinari.groupcommunication.models.groups.repo.schedule

import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ScheduleRepo {
    @GET("./getschedule")
    suspend fun getGroupSchedule(@Query("group") group: String, @Query("secret") groupSecret: String): List<Lesson>

    @POST("./setimportant")
    suspend fun setGroupImportantLesson(
        @Query("group") group: String,
        @Query("secret") groupSecret: String,
        @Query("user") user: Short,
        @Query("flag") flag: Short,
        @Query("lessonWeekNum") weekNum: Short,
        @Query("lessonDay") lessonInDay: Short)
}
