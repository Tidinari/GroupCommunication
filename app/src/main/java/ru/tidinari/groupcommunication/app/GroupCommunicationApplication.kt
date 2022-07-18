package ru.tidinari.groupcommunication.app

import android.app.Application
import ru.tidinari.groupcommunication.models.groups.repo.communication.ChatRepo
import ru.tidinari.groupcommunication.models.groups.repo.entrance.EntryRepo
import ru.tidinari.groupcommunication.models.groups.repo.schedule.ScheduleRepo

class GroupCommunicationApplication: Application() {

    private lateinit var communicationService: ChatRepo
    private lateinit var scheduleRepo: ScheduleRepo
    private lateinit var entryRepo: EntryRepo

    override fun onCreate() {
        val retrofit = RetrofitFactory.instance.retrofitClient
        communicationService = retrofit.create(ChatRepo::class.java)
        scheduleRepo = retrofit.create(ScheduleRepo::class.java)
        entryRepo = retrofit.create(EntryRepo::class.java)
    }
}