package ru.tidinari.groupcommunication.models.groups

import kotlinx.serialization.Serializable
import ru.tidinari.groupcommunication.models.groups.repo.entrance.User

@Serializable
data class Group(
    val group: String = "ТЕСТ-00-00",
    val groupSecret: String,
    val userSecret: User
)