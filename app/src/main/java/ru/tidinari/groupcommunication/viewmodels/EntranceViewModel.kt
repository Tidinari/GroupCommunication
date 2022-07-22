package ru.tidinari.groupcommunication.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.tidinari.groupcommunication.app.GroupCommunicationApplication
import ru.tidinari.groupcommunication.app.RetrofitFactory
import ru.tidinari.groupcommunication.models.groups.repo.entrance.EntryRepo

class EntranceViewModel : ViewModel() {
    private val entryRepo: EntryRepo

    init {
        val retrofit = RetrofitFactory.instance.retrofitClient
        entryRepo = retrofit.create(EntryRepo::class.java)
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                _groupList.addAll(entryRepo.getGroups())
            }
        }
    }

    private val _groupList: MutableList<String> = mutableListOf()
    val groupList: List<String> = _groupList

    fun saveGroupLocally(group: String) {
        val localStorage = GroupCommunicationApplication.localRepo.edit()
        localStorage.putString("group", group)
        localStorage.commit()
    }
}