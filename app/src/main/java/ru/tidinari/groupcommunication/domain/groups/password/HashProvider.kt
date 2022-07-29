package ru.tidinari.groupcommunication.domain.groups.password

interface HashProvider {
    fun hash(password: String): String
}