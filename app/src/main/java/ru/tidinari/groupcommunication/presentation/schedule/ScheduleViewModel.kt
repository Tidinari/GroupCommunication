package ru.tidinari.groupcommunication.presentation.schedule

import android.os.Environment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import ru.tidinari.groupcommunication.app.GroupCommApplication
import ru.tidinari.groupcommunication.data.network.RetrofitFactory
import ru.tidinari.groupcommunication.data.models.Group
import ru.tidinari.groupcommunication.data.models.Schedule
import ru.tidinari.groupcommunication.domain.repo.ScheduleRepo
import java.io.File

class ScheduleViewModel(private val group: Group) : ViewModel() {

    private val localScheduleFile by lazy {
        File(GroupCommApplication.localFileDir, "schedule-$group.json")
    }
    private val _schedule: MutableLiveData<Schedule?> = MutableLiveData()
    val schedule: LiveData<Schedule?> = _schedule
    var usedRemoteStorage = false
     private set

    fun getRemoteSchedule() {
        usedRemoteStorage = true
        val retrofit = RetrofitFactory.instance.retrofitClient
        val scheduleRepo = retrofit.create(ScheduleRepo::class.java)
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val remoteSchedule = scheduleRepo.getGroupSchedule(group)
                _schedule.postValue(remoteSchedule.toData())
            }
        }
    }

    fun writeScheduleToLocalStorage(): Boolean {
        if (isExternalStorageAvailable()) return false
        if (schedule.value != null) return false

        localScheduleFile.writeText(Json.encodeToString(schedule.value))
        return true
    }

    fun getLocalSchedule(): Boolean {
        if (isExternalStorageAvailable()) return false
        if (!isLocalSchedulePresented()) return false

        val localSchedule: Schedule = Json.decodeFromString(
            localScheduleFile.readText()
        )
        usedRemoteStorage = false
        _schedule.postValue(localSchedule)
        return true
    }

    private fun isExternalStorageAvailable(): Boolean {
        val state = Environment.getExternalStorageState()
        if (state == Environment.MEDIA_UNMOUNTED || state == Environment.MEDIA_MOUNTED_READ_ONLY) {
            return true
        }
        return false
    }

    private fun isLocalSchedulePresented(): Boolean {
        return localScheduleFile.exists()
    }
}