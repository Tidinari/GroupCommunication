package ru.tidinari.groupcommunication.domain.repo

import retrofit2.http.GET
import ru.tidinari.groupcommunication.data.Group
import ru.tidinari.groupcommunication.domain.models.DomainSchedule

interface ScheduleRepo {
    @GET("./generated-data")
    suspend fun getGroupSchedule(group: Group): DomainSchedule
}
