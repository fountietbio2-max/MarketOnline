package com.example.mbokoshop.Data

import android.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

data class NavigationItem(
    val title: String,
    val icon: androidx.compose.ui.graphics.vector.ImageVector,
    val route: String
)

// Modèle de données pour une catégorie
data class CategorieData(val name: String, val icon: ImageVector, val color: Color)
