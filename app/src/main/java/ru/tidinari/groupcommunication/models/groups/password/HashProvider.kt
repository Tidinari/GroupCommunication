package ru.tidinari.groupcommunication.models.groups.password

interface HashProvider {
    fun hash(password: String): String
    fun hash(password: String, salt: String): String
}