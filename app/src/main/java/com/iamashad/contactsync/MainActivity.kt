package com.iamashad.contactsync

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.iamashad.contactsync.screens.ContactSyncApp
import com.iamashad.contactsync.ui.theme.ContactSyncTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ContactSyncTheme {
                ContactSyncApp()
            }
        }
    }
}