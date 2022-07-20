package ru.tidinari.groupcommunication.viewmodels.entrance

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import ru.tidinari.groupcommunication.app.GroupCommunicationApplication
import ru.tidinari.groupcommunication.app.RetrofitFactory
import ru.tidinari.groupcommunication.models.groups.Group
import ru.tidinari.groupcommunication.models.groups.password.Password
import ru.tidinari.groupcommunication.models.groups.repo.entrance.EntryRepo
import ru.tidinari.groupcommunication.models.groups.repo.entrance.User

class EntranceViewModel : ViewModel() {
    private val entryRepo: EntryRepo

    init {
        val retrofit = RetrofitFactory.instance.retrofitClient
        entryRepo = retrofit.create(EntryRepo::class.java)
    }

    private val _userGroup: MutableLiveData<Group?> = MutableLiveData()
    val group: LiveData<Group?> = _userGroup

    private val _groupList: MutableList<String> = mutableListOf()
    val groupList: List<String> = _groupList

    fun getLocalUser(): User? {
        val hash = GroupCommunicationApplication.localRepo.getString("user", null)
        return hash?.let { User(it) }
    }

    fun getGroups() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                _groupList.addAll(entryRepo.getGroups())
            }
        }
    }

    fun signUp(group: String, _password: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val password = Password(_password)
                val response = try {
                    entryRepo.signUp(group, password.hash)
                } catch (e: Exception) {
                    null
                }
                if (response != null) {
                    GroupCommunicationApplication.localRepo.edit()
                        .putString("user", response.userSecret.userHash)
                        .commit()
                }
                _userGroup.postValue(response)
            }
        }
    }

    fun signIn(user: User) {
        viewModelScope.launch {
            val response: Group? = try {
                entryRepo.signIn(user)
            } catch (e: Exception) {
                null
            }
            if (response != null) {
                _userGroup.postValue(response)
            } else {
                _userGroup.postValue(null)
            }
        }
    }
}