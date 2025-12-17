package com.example.sportsterlitamak

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.sportsterlitamak.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val placeId = intent.getIntExtra("PLACE_ID", -1)

        // Ищем место в НОВОЙ структуре данных
        val place = DataSource.sportPlaces.find { it.id == placeId }

        if (place == null) {
            Toast.makeText(this, "Объект не найден", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        // Заполняем данными
        binding.tvDetailName.text = place.name

        // Получаем название вида спорта по ID
        val sportTypeName = DataSource.sportTypes.find { it.id == place.sportTypeId }?.name ?: ""
        binding.tvDetailSports.text = "🏆 Вид спорта: $sportTypeName"

        binding.tvDetailAddress.text = "📍 Адрес: ${place.address}"
        binding.tvDetailSchedule.text = "🕒 Время работы: ${place.schedule}"
        binding.tvDetailCoach.text = "👨‍🏫 Тренер: ${place.coachInfo}"
        binding.tvDetailPrice.text = "💰 Условия (Цена): ${place.priceInfo}"
        binding.tvDetailPhone.text = "📞 Телефон: ${place.phone}"
        binding.tvDetailDescription.text = "📝 Описание: ${place.description}"

        // Кнопка "Показать на карте" - проверяем новые поля latitude и longitude
        binding.btnShowOnMap.setOnClickListener {
            // В новой структуре у нас нет полей latitude/longitude
            // Используем просто поиск по адресу
            val gmmIntentUri = Uri.parse("geo:0,0?q=${Uri.encode(place.name + ", " + place.address + ", Стерлитамак")}")
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps")
            if (mapIntent.resolveActivity(packageManager) != null) {
                startActivity(mapIntent)
            } else {
                Toast.makeText(this, "Установите Google Карты", Toast.LENGTH_SHORT).show()
            }
        }

        // Кнопка "Позвонить"
        binding.btnCall.setOnClickListener {
            if (place.phone.isNotEmpty()) {
                val intent = Intent(Intent.ACTION_DIAL)
                intent.data = Uri.parse("tel:${place.phone}")
                startActivity(intent)
            } else {
                Toast.makeText(this, "Телефон не указан", Toast.LENGTH_SHORT).show()
            }
        }

        // Кнопка "Поделиться"
        binding.btnShare.setOnClickListener {
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "text/plain"
            val shareText = """
                ${place.name}
                Вид спорта: ${DataSource.sportTypes.find { it.id == place.sportTypeId }?.name ?: ""}
                Адрес: ${place.address}
                Время работы: ${place.schedule}
                Тренер: ${place.coachInfo}
                Цена: ${place.priceInfo}
                Телефон: ${place.phone}
            """.trimIndent()
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareText)
            startActivity(Intent.createChooser(shareIntent, "Поделиться информацией"))
        }
    }
}