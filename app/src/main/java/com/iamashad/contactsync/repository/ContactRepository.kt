package com.iamashad.contactsync.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.iamashad.contactsync.model.User
import kotlinx.coroutines.tasks.await
import java.util.*
import javax.inject.Inject

class ContactRepository @Inject constructor(
    private val firestore: FirebaseFirestore
) {
    suspend fun getTodayUsers(): List<User> {
        return try {

            val startOfDay = Calendar.getInstance().apply {
                set(Calendar.HOUR_OF_DAY, 0)
                set(Calendar.MINUTE, 0)
                set(Calendar.SECOND, 0)
                set(Calendar.MILLISECOND, 0)
            }.time

            val endOfDay = Calendar.getInstance().apply {
                set(Calendar.HOUR_OF_DAY, 23)
                set(Calendar.MINUTE, 59)
                set(Calendar.SECOND, 59)
                set(Calendar.MILLISECOND, 999)
            }.time

            val snapshot: QuerySnapshot = firestore.collection("Users")
                .whereGreaterThanOrEqualTo("createdAt", startOfDay)
                .whereLessThanOrEqualTo("createdAt", endOfDay)
                .get()
                .await()

            snapshot.documents.mapNotNull { document ->
                document.toObject(User::class.java)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }
}
