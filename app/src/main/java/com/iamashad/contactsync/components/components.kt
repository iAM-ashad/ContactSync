package com.iamashad.contactsync.components

import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar() {
    TopAppBar(
        title = {
            Text(
                text = "Contact Sync App",
                fontWeight = FontWeight.Bold
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(103, 58, 183, 255),
            titleContentColor = Color(255,255,255),
        )
    )
}

@Composable
fun AppButtons(
    buttonText: String,
    onClick: () -> Unit,
    buttonColor: Color
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = buttonColor
        ),
        shape = RoundedCornerShape(corner = CornerSize(3.dp)),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 5.dp
        )
    ) {
        Text(
            text = buttonText
        )
    }
}


@Preview(showBackground = true)
@Composable
fun ComponentPreview() {
    AppButtons(
        buttonText = "Sync Contacts",
        onClick = {},
        buttonColor = Color(103, 58, 183, 255)
    )
}