package ru.tidinari.groupcommunication.app

import android.app.Application
import android.content.SharedPreferences
import java.io.File

class GroupCommunicationApplication : Application() {

    companion object {
        lateinit var sharedPreferences: SharedPreferences
        var localFileDir: File? = null
    }

    override fun onCreate() {
        val preferencesKey = "GroupCommunicationApp"
        sharedPreferences = getSharedPreferences(preferencesKey, MODE_PRIVATE)
        localFileDir = getExternalFilesDir(null)?.let {
            it.mkdir()
            it
        }
    }
}