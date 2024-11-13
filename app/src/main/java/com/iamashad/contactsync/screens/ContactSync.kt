package com.iamashad.contactsync.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.iamashad.contactsync.components.AppButtons
import com.iamashad.contactsync.components.AppTopBar
import com.iamashad.contactsync.ui.theme.ContactSyncTheme

@Composable
fun ContactSyncApp() {
    Scaffold (
        topBar = {
            AppTopBar()
        }
    ) {contentPadding->
        Column (
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
        ) {
            AppButtons(
                buttonText = "Sync Contact",
                buttonColor = Color(93, 31, 210, 255),
                onClick = {}
            )
            Text(
                text = "Add all new contacts",
                color = Color.Gray,
                fontSize = 12.sp
            )
            Row(
                modifier = Modifier
            ) {
                Text(
                    text = "Progress: ",
                    color = Color.Gray,
                    fontSize = 12.sp
                )
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