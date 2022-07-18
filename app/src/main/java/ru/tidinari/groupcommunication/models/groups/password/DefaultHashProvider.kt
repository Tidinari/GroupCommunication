package ru.tidinari.groupcommunication.models.groups.password

import java.security.MessageDigest
import kotlin.random.Random

class DefaultHashProvider: HashProvider {
    override fun hash(password: String): String {
        val salt = generateString()
        return hash(password, salt)
    }

    override fun hash(password: String, salt: String): String {
        val hashProvider = MessageDigest.getInstance("SHA-256")
        val hashedDataBytes: ByteArray = hashProvider.digest((password + salt).toByteArray())
        val builder = StringBuilder()
        builder.append(bytesToHex(hashedDataBytes))
        builder.append('%')
        builder.append(salt)
        return builder.toString()
    }

    private fun generateString(size: Int = 6): String {
        val random = Random.Default
        val builder = StringBuilder()

        for (i in 0..size) {
            builder.append(Char(random.nextInt(97, 122)))
        }
        return builder.toString()
    }

    private fun bytesToHex(bytes: ByteArray): String {
        val result = StringBuffer(bytes.size * 2)
        for (byte in bytes)
            result.append(((0xff and byte.toInt()) + 0x100).toString(16).substring(1))
        return result.toString()
    }
}
