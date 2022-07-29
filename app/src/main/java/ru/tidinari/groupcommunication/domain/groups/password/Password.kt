package ru.tidinari.groupcommunication.domain.groups.password

class Password(rawPassword: String, hashProvider: HashProvider = DefaultHashProvider()) {
    val hash: String

    init {
        hash = hashProvider.hash(rawPassword)
    }
}