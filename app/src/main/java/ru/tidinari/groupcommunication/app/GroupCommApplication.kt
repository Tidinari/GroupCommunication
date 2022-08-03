package ru.tidinari.groupcommunication.app

import android.app.Application
import android.content.SharedPreferences
import android.widget.Toast
import ru.tidinari.groupcommunication.data.models.Group
import java.io.File

class GroupCommApplication : Application() {

    companion object {
        lateinit var sharedPreferences: SharedPreferences
        var localFileDir: File? = null
        lateinit var instance: GroupCommApplication
        fun toast(stringId: Int, duration: Int = Toast.LENGTH_LONG) {
            Toast.makeText(instance.baseContext, stringId, duration).show()
        }

        var group: Group?
            get() {
                val group = sharedPreferences.getString("group", null)
                return if (group.isNullOrEmpty()) {
                    null
                } else {
                    Group(group)
                }
            }
            set(value) {
                if (value != null) {
                    sharedPreferences.edit().putString("group", value.group).apply()
                }
            }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        val preferencesKey = "GroupCommunicationApp"
        sharedPreferences = getSharedPreferences(preferencesKey, MODE_PRIVATE)
        localFileDir = getExternalFilesDir(null)?.let {
            it.mkdir()
            it
        }
    }
}