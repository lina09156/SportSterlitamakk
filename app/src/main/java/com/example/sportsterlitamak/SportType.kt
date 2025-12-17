package com.example.sportsterlitamak

data class SportType(
    val id: Int,
    val name: String,
    val iconRes: Int, // Для иконки (можно оставить 0 если нет иконок)
    val color: String // HEX цвет
)