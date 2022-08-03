package ru.tidinari.groupcommunication.domain.models

import kotlinx.serialization.Serializable
import ru.tidinari.groupcommunication.data.models.Group

@Serializable
data class DomainGroup(val group: String) {
    fun toData(): Group {
        return Group(group)
    }
}