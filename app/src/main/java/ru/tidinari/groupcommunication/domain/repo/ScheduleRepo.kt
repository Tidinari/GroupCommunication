package ru.tidinari.groupcommunication.domain.repo

import retrofit2.http.GET
import retrofit2.http.Query
import ru.tidinari.groupcommunication.data.models.Group
import ru.tidinari.groupcommunication.domain.models.DomainSchedule

interface ScheduleRepo {
    @GET("./generated-data")
    suspend fun getGroupSchedule(@Query("group") group: Group): DomainSchedule
}
