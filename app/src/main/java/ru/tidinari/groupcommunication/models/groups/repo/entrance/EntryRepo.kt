package ru.tidinari.groupcommunication.models.groups.repo.entrance

import retrofit2.http.GET
import retrofit2.http.Query
import ru.tidinari.groupcommunication.models.groups.Group

interface EntryRepo {
    @GET("./groups")
    suspend fun getGroups(): List<String>
}