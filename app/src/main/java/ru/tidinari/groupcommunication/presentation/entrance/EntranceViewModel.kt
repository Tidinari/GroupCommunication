package ru.tidinari.groupcommunication.presentation.entrance

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.tidinari.groupcommunication.app.GroupCommApplication
import ru.tidinari.groupcommunication.data.network.RetrofitFactory
import ru.tidinari.groupcommunication.data.models.Group
import ru.tidinari.groupcommunication.domain.repo.EntryRepo

class EntranceViewModel : ViewModel() {
    private val entryRepo: EntryRepo

    init {
        val retrofit = RetrofitFactory.instance.retrofitClient
        entryRepo = retrofit.create(EntryRepo::class.java)
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                _groupList.addAll(entryRepo.getGroups().map { it.toData() })
            }
        }
    }

    private val _groupList: MutableList<Group> = mutableListOf()
    val groupList: List<Group> = _groupList

    fun saveGroupLocally(group: Group) {
        GroupCommApplication.group = group
    }
}