package com.example.sportsterlitamak

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.sportsterlitamak.databinding.ActivityPlaceDetailBinding

class PlaceDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPlaceDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPlaceDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val placeId = intent.getIntExtra("PLACE_ID", -1)
        val place = DataSource.sportPlaces.find { it.id == placeId }

        if (place == null) {
            Toast.makeText(this, "Место не найдено", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        setupUI(place)
    }

    private fun setupUI(place: SportPlace) {
        binding.tvPlaceName.text = place.name
        binding.tvAddress.text = "📍 Адрес: ${place.address}"
        binding.tvSchedule.text = "🕒 Время работы:\n${place.schedule}"
        binding.tvCoach.text = "👨‍🏫 Тренер:\n${place.coachInfo}"
        binding.tvPrice.text = "💰 Условия (Цена):\n${place.priceInfo}"
        binding.tvPhone.text = "📞 Телефон: ${place.phone}"
        binding.tvDescription.text = "📝 Описание:\n${place.description}"

        binding.ivBack.setOnClickListener {
            finish()
        }

        binding.btnCall.setOnClickListener {
            if (place.phone.isNotEmpty()) {
                val intent = Intent(Intent.ACTION_DIAL)
                intent.data = Uri.parse("tel:${place.phone}")
                startActivity(intent)
            } else {
                Toast.makeText(this, "Телефон не указан", Toast.LENGTH_SHORT).show()
            }
        }

        // Кнопка "Показать на карте в приложении"
        binding.btnShowOnAppMap.setOnClickListener {
            val intent = Intent(this, MapActivity::class.java)
            intent.putExtra("PLACE_ID", place.id)
            startActivity(intent)
        }

        // Кнопка "Открыть в Яндекс Картах"
        binding.btnShowOnYandexMap.setOnClickListener {
            val yandexUrl = if (place.yandexMapsUrl.isNotEmpty()) {
                place.yandexMapsUrl
            } else {
                "https://yandex.ru/maps/?text=${Uri.encode(place.name + ", " + place.address + ", Стерлитамак")}"
            }

            val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse(yandexUrl))
            startActivity(webIntent)
        }

        // Кнопка "Поделиться"
        binding.btnShare.setOnClickListener {
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "text/plain"

            val shareText = """
                🏆 ${place.name}
                
                📍 Адрес: ${place.address}
                🕒 Время работы: ${place.schedule}
                👨‍🏫 Тренер: ${place.coachInfo}
                💰 Цена: ${place.priceInfo}
                📞 Телефон: ${place.phone}
                
                Скачайте приложение "Спорт Стерлитамак"!
            """.trimIndent()

            shareIntent.putExtra(Intent.EXTRA_TEXT, shareText)
            startActivity(Intent.createChooser(shareIntent, "Поделиться"))
        }
    }
}