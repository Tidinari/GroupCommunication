package ru.tidinari.groupcommunication.models.groups.repo.communication

import kotlinx.serialization.Serializable

@Serializable
data class Chat(val title: String, val hash: String)
