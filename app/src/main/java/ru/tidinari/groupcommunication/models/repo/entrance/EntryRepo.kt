package ru.tidinari.groupcommunication.models.repo.entrance

import retrofit2.http.GET

interface EntryRepo {
    @GET("./groups")
    suspend fun getGroups(): List<String>
}