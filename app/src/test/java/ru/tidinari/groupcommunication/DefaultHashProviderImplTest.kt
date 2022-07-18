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
        val hash = hashProvider.hash("password", "salt")
        assertEquals("7a37b85c8918eac19a9089c0fa5a2ab4dce3f90528dcdeec108b23ddf3607b99%salt", hash)
    }
}