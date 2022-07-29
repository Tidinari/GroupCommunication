package ru.tidinari.groupcommunication.domain.groups.password

import java.security.MessageDigest

class DefaultHashProvider: HashProvider {
    override fun hash(password: String): String {
        val hashProvider = MessageDigest.getInstance("SHA-256")
        val hashedDataBytes: ByteArray = hashProvider.digest((password).toByteArray())
        return bytesToHex(hashedDataBytes)
    }

    private fun bytesToHex(bytes: ByteArray): String {
        val result = StringBuffer(bytes.size * 2)
        for (byte in bytes)
            result.append(((0xff and byte.toInt()) + 0x100).toString(16).substring(1))
        return result.toString()
    }
}
