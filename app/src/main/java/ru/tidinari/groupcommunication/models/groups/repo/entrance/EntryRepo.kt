package ru.tidinari.groupcommunication.models.groups.repo.entrance

import retrofit2.http.GET

interface EntryRepo {
    @GET("./signin")
    suspend fun signIn(group: String, hash: String): User
}