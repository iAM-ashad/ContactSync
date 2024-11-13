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

    private val _users = MutableStateFlow<List<User>>(emptyList()) // Holds the list of users
    val users: StateFlow<List<User>> = _users

    private val _progress = MutableStateFlow(0) // Tracks progress percentage
    val progress: StateFlow<Int> = _progress

    private val _isSyncing = MutableStateFlow(false) // Indicates if syncing is in progress
    val isSyncing: StateFlow<Boolean> = _isSyncing

    /**
     * Fetches today's users from the repository and updates the state.
     */
    fun fetchTodayUsers() {
        viewModelScope.launch {
            try {
                val userList = contactRepository.getTodayUsers() // Fetch today's users
                _users.value = userList
            } catch (e: Exception) {
                e.printStackTrace()
                _users.value = emptyList() // Reset the user list on error
            }
        }
    }

    /**
     * Syncs today's contacts to the device and updates progress and syncing state.
     */
    fun syncContactsToDevice() {
        viewModelScope.launch {
            try {
                Log.d("ContactSync", "Setting isSyncing to true")
                _isSyncing.value = true
                val users = contactRepository.getTodayUsers() // Fetch today's users
                val totalUsers = users.size
                _progress.value = 0

                users.forEachIndexed { index, user ->
                    addContactToDevice(app, user.name, user.phone) // Sync contact to device
                    delay(300) // Simulate delay for progress visualization
                    _progress.value = ((index + 1) * 100) / totalUsers // Update progress
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
