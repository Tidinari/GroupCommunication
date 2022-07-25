package ru.tidinari.groupcommunication.models.groups

import kotlinx.serialization.Serializable

@Serializable
data class Group(
    val group: String = "ТЕСТ-00-00"
)