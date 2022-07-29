package ru.tidinari.groupcommunication.domain.repo.entrance

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@JvmInline
@Serializable
value class User(@SerialName("id") val userHash: String)
