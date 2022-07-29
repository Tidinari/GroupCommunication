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
import ru.tidinari.groupcommunication.app.GroupCommunicationApplication
import ru.tidinari.groupcommunication.app.RetrofitFactory
import ru.tidinari.groupcommunication.domain.repo.schedule.Lesson
import ru.tidinari.groupcommunication.domain.repo.schedule.ScheduleRepo
import java.io.File

class ScheduleViewModel(private val group: String) : ViewModel() {

    private val localScheduleFile by lazy {
        File(GroupCommunicationApplication.localFileDir, "schedule-$group.json")
    }
    private val _schedule: MutableLiveData<Map<Int, List<Lesson>>> = MutableLiveData()
    val schedule: LiveData<Map<Int, List<Lesson>>> = _schedule
    var usedRemoteStorage = false
     private set

    fun getRemoteSchedule() {
        usedRemoteStorage = true
        val retrofit = RetrofitFactory.instance.retrofitClient
        val scheduleRepo = retrofit.create(ScheduleRepo::class.java)
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val remoteSchedule = scheduleRepo.getGroupSchedule(group)
                _schedule.postValue(remoteSchedule)
            }
        }
    }

    fun writeScheduleToLocalStorage(): Boolean {
        if (isExternalStorageAvailable()) return false

        localScheduleFile.writeText(Json.encodeToString(schedule.value))
        return true
    }

    fun getLocalSchedule(): Boolean {
        if (isExternalStorageAvailable()) return false

        val localSchedule: Map<Int, List<Lesson>> = Json.decodeFromString(
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

    fun isLocalSchedulePresented(): Boolean {
        return localScheduleFile.exists()
    }
}