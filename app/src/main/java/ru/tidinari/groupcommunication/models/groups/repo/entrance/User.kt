package ru.tidinari.groupcommunication.models.groups.repo.entrance

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class User(@SerialName("id") val groupId: Short)
