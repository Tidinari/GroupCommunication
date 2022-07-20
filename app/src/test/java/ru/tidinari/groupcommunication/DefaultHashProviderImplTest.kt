package ru.tidinari.groupcommunication

import org.junit.Before
import org.junit.Test
import ru.tidinari.groupcommunication.models.groups.password.DefaultHashProvider
import ru.tidinari.groupcommunication.models.groups.password.HashProvider
import org.junit.Assert.*

class DefaultHashProviderImplTest {

    private lateinit var hashProvider: HashProvider

    @Before
    fun setUpImplementation() {
        hashProvider = DefaultHashProvider()
    }

    @Test
    fun testCorrectHashOutput() {
        val hash = hashProvider.hash("password")
        assertEquals("5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8", hash)
    }
}