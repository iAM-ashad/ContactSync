package com.iamashad.contactsync.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.iamashad.contactsync.components.AppButtons
import com.iamashad.contactsync.components.AppTopBar
import com.iamashad.contactsync.ui.theme.ContactSyncTheme

@Composable
fun ContactSyncApp(
    viewModel: ContactViewModel = hiltViewModel()
) {
    val isSyncing by viewModel.isSyncing.collectAsState()
    val progress by viewModel.progress.collectAsState()

    println("Recomposition triggered: isSyncing=$isSyncing, progress=$progress")

    Scaffold(
        topBar = {
            AppTopBar()
        }
    ) { contentPadding ->
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
        ) {
            AppButtons(
                isSyncing = isSyncing,
                buttonText = if (isSyncing) "Syncing..." else "Sync Contacts",
                buttonColor = Color(93, 31, 210, 255),
                onClick = {
                    if (!isSyncing) {
                        viewModel.syncContactsToDevice()
                    }
                }
            )
            Text(
                text = "Add all new contacts",
                color = Color.Gray,
                fontSize = 12.sp
            )
            if (isSyncing) {
                Spacer(modifier = Modifier.height(4.dp))
                Row (
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "Progress: ",
                        color = Color.Gray,
                        fontSize = 12.sp
                    )
                    CircularProgressIndicator(
                        progress = progress / 100f,
                        modifier = Modifier
                            .scale(.5f)
                    )
                }
            }
            Spacer(modifier = Modifier.padding(vertical = 20.dp))
            AppButtons(
                buttonText = "Delete Contacts",
                buttonColor = Color(159, 6, 9, 255),
                onClick = {}
            )
            Text(
                text = "Delete all contacts older than a month",
                color = Color.Gray,
                fontSize = 12.sp
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ScreenPreview() {
    ContactSyncTheme {
        ContactSyncApp()
    }
}