package ru.tidinari.groupcommunication.models.groups.repo.entrance

import retrofit2.http.GET
import retrofit2.http.Query
import ru.tidinari.groupcommunication.models.groups.Group

interface EntryRepo {
    @GET("./signup")
    suspend fun signUp(@Query("group") group: String, @Query("password") passwordHash: String): Group?

    @GET("./signin")
    suspend fun signIn(@Query("user") userHash: User): Group?

    @GET("./groups")
    suspend fun getGroups(): List<String>
}