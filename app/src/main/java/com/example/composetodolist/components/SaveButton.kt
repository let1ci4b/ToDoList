package com.example.composetodolist.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.composetodolist.ui.theme.Green
import com.example.composetodolist.ui.theme.White

@Composable
fun saveButton(
    onClick: () -> Unit,
    modifier: Modifier,
    text: String
){
    Button(
        onClick,
        modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = Green,
            contentColor = White
        ),
        shape = RoundedCornerShape(10)
    ) {
        Text(
            text = text,
            fontWeight = FontWeight.Medium,
            fontSize = 18.sp
        )
    }
}