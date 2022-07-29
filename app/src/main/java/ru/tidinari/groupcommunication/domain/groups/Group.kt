package ru.tidinari.groupcommunication.domain.groups

import kotlinx.serialization.Serializable

@Serializable
data class Group(
    val group: String = "ТЕСТ-00-00"
)