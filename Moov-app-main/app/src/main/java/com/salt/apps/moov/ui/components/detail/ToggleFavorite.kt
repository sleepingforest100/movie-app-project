package com.salt.apps.moov.ui.components.detail

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun ToggleFavorite(
    modifier: Modifier = Modifier,
    onToggle: (Boolean) -> Unit,
    isFavorite: Boolean = false
) {
    var isChecked by remember { mutableStateOf(isFavorite) }

    IconToggleButton(
        modifier = modifier,
        checked = isChecked,
        onCheckedChange = {
            isChecked = it
            onToggle(it)
        }
    ) {
        val icon = if (isChecked) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder
        val iconTint = if (isChecked) Color.Red else Color.Red

        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = iconTint,
        )
    }
}