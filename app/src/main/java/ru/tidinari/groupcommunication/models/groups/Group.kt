package ru.tidinari.groupcommunication.models.groups

import ru.tidinari.groupcommunication.models.groups.password.Password

data class Group(val group: String, val password: Password)