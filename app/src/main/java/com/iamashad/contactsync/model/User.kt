package com.iamashad.contactsync.model

import com.google.firebase.Timestamp

data class User(
    val name: String = "",
    val phone: String = "",
    val createdAt: Timestamp? = null
)
