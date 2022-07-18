package ru.tidinari.groupcommunication.models.groups.repo.communication

import retrofit2.http.GET
import retrofit2.http.POST

interface ChatRepo {
    @GET("./getchats")
    suspend fun getGroupChats(group: String, hash: String): List<Chat>

    @GET("./getmessages")
    suspend fun getGroupChatMessages(group: String, hash: String, chatId: Int): List<Message>

    @POST("./send")
    suspend fun sendMessage(group: String, hash: String, user: Short)
}