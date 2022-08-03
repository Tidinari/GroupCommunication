package ru.tidinari.groupcommunication.domain.repo

import retrofit2.http.GET
import ru.tidinari.groupcommunication.domain.models.DomainGroup

interface EntryRepo {
    @GET("./groups")
    suspend fun getGroups(): List<DomainGroup>
}