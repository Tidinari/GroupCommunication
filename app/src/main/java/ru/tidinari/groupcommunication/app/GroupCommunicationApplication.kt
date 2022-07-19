package ru.tidinari.groupcommunication.app

import android.app.Application
import android.content.SharedPreferences

class GroupCommunicationApplication : Application() {

    companion object {
        lateinit var localRepo: SharedPreferences
    }

    override fun onCreate() {
        val preferencesKey = "GroupCommunicationApp"
        localRepo = getSharedPreferences(preferencesKey, MODE_PRIVATE)
    }
}