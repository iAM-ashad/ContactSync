package com.iamashad.contactsync.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.iamashad.contactsync.model.User
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ContactRepository @Inject constructor(
    private val firestore: FirebaseFirestore
) {
    suspend fun getTodayUsers(): List<User> {
        return try {
            val snapshot = firestore.collection("Users").get().await()
            snapshot.documents.mapNotNull { document ->
                document.toObject(User::class.java)
            }
        } catch (e: Exception) {
            emptyList()
        }
    }
}