package ru.tidinari.groupcommunication.models.groups.repo.communication

import kotlinx.serialization.Serializable

@Serializable
data class Message(val user: Short, val message: String)
