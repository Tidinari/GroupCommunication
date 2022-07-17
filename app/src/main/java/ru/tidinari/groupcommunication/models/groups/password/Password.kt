package ru.tidinari.groupcommunication.models.groups.password

class Password(rawPassword: String, hashProvider: HashProvider) {
    val password: String

    init {
        password = hashProvider.hash(rawPassword)
    }
}