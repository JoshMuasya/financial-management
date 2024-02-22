package com.example.finance.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.example.finance.R

@Composable
fun PasswordInput (
    value: String,
    onValueChange: (String) -> Unit,
    error: String?
) {
    val showPassword = remember { mutableStateOf(false) }

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text("Email") },
        isError = error != null,
        trailingIcon = {
            IconButton(
                onClick = { showPassword.value = !showPassword.value }
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(
                        if (showPassword.value) R.drawable.ic_android_black_24dp
                        else R.drawable.ic_android_black_24dp
                    ),
                    tint = Color.Gray,
                    contentDescription = null
                )
            }
        },
        visualTransformation = if (showPassword.value) VisualTransformation.None else PasswordVisualTransformation()
    )
}