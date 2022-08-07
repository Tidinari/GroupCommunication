package ru.tidinari.groupcommunication.data.models

import kotlinx.serialization.Serializable

interface Schedule {
    fun getGroups(): Set<Group>
    fun getSchedule(group: Group): List<Lesson>?
}

@Serializable
data class FacultySchedule(private val schedule: Map<Group, List<Lesson>>) : Schedule {
    override fun getGroups() = schedule.keys
    override fun getSchedule(group: Group): List<Lesson>? = schedule[group]
}