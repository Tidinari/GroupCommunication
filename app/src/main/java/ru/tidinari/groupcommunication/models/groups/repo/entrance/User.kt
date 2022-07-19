package ru.tidinari.groupcommunication.models.groups.repo.entrance

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@JvmInline
@Serializable
value class User(@SerialName("id") val userHash: String)
