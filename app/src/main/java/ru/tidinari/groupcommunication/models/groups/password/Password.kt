package ru.tidinari.groupcommunication.models.groups.password

class Password(rawPassword: String, hashProvider: HashProvider = DefaultHashProvider()) {
    val hash: String

    init {
        hash = hashProvider.hash(rawPassword)
    }
}