package com.example.sportsterlitamak

data class SportPlace(
    val id: Int,
    val name: String,
    val sportTypeId: Int,
    val address: String,
    val schedule: String,
    val coachInfo: String,
    val priceInfo: String,
    val phone: String = "",
    val description: String = "",
    val rating: Float = 0f,
    val latitude: Double,      // Широта
    val longitude: Double,     // Долгота
    val yandexMapsUrl: String = "" // Ссылка на Яндекс Карты
)