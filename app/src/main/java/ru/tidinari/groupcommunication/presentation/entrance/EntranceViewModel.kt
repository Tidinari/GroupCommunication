package ru.tidinari.groupcommunication.presentation.entrance

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.tidinari.groupcommunication.app.GroupCommApplication
import ru.tidinari.groupcommunication.data.network.RetrofitFactory
import ru.tidinari.groupcommunication.data.models.Group

class EntranceViewModel : ViewModel() {

    init {
        val retrofit = RetrofitFactory.instance.retrofitClient
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
            }
        }
    }

    private val _groupList: MutableList<Group> = mutableListOf()
    val groupList: List<Group> = _groupList

    fun saveGroupLocally(group: Group) {
        GroupCommApplication.group = group
    }
}