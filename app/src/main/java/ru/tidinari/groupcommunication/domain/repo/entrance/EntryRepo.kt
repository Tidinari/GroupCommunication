package ru.tidinari.groupcommunication.domain.repo.entrance

import retrofit2.http.GET

interface EntryRepo {
    @GET("./groups")
    suspend fun getGroups(): List<String>
}