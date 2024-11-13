package com.iamashad.contactsync.screens

import android.app.Application
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iamashad.contactsync.model.User
import com.iamashad.contactsync.repository.ContactRepository
import com.iamashad.contactsync.utils.addContactToDevice
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContactViewModel @Inject constructor(
    private val contactRepository: ContactRepository,
    private val app: Application
) : ViewModel() {

    private val _users = MutableStateFlow<List<User>>(emptyList())
    val users: StateFlow<List<User>> = _users

    private val _progress = MutableStateFlow(0)
    val progress: StateFlow<Int> = _progress

    private val _isSyncing = MutableStateFlow(false)
    val isSyncing: StateFlow<Boolean> = _isSyncing

    fun fetchTodayUsers() {
        viewModelScope.launch {
            val userList = contactRepository.getTodayUsers()
            _users.value = userList
        }
    }

    fun syncContactsToDevice() {
        viewModelScope.launch {
            try {
                Log.d("isSyncStatus", "Setting isSyncing to true")
                _isSyncing.value = true
                val users = contactRepository.getTodayUsers()
                val totalUsers = users.size
                _progress.value = 0

                users.forEachIndexed { index, user ->
                    addContactToDevice(app, user.name, user.phone)
                    delay(300)
                    _progress.value = ((index + 1) * 100) / totalUsers
                    Log.d("ProgressStatus", "Progress updated to ${_progress.value}")
                }
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                Log.d("ContactSync", "Setting isSyncing to false")
                _isSyncing.value = false
            }
        }
    }

}
