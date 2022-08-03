package ru.tidinari.groupcommunication.domain.models

import kotlinx.serialization.Serializable
import ru.tidinari.groupcommunication.data.Group

@Serializable
data class DomainGroup(val group: String, val columnIndex: Int) {
    fun toData(): Group {
        return Group(group)
    }
}