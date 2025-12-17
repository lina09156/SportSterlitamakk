package com.example.sportsterlitamak

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sportsterlitamak.databinding.ItemSportTypeBinding
import com.google.android.material.card.MaterialCardView

class SportTypeAdapter(
    private val sportTypes: List<SportType>,
    private val onItemClick: (SportType) -> Unit
) : RecyclerView.Adapter<SportTypeAdapter.ViewHolder>() {

    // Иконки для ВСЕХ видов спорта
    private val sportIcons = mapOf(
        "Футбол" to "⚽",
        "Баскетбол" to "🏀",
        "Теннис" to "🎾",
        "Плавание" to "🏊",
        "Единоборства" to "🥋",
        "Фитнес" to "💪",
        "Хоккей" to "🏒",
        "Волейбол" to "🏐",
        "Йога" to "🧘",
        "Бокс" to "🥊",
        "Трена.зал" to "🏋️"
    )

    class ViewHolder(val binding: ItemSportTypeBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemSportTypeBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val sportType = sportTypes[position]
        val placesCount = DataSource.getPlacesBySportType(sportType.id).size

        // Удаляем эмодзи из названия (оставляем только текст после пробела)
        val nameParts = sportType.name.split(" ", limit = 2)
        val cleanName = if (nameParts.size > 1) nameParts[1] else sportType.name

        // Устанавливаем иконку сверху - ищем по чистому названию
        holder.binding.ivIcon.text = sportIcons[cleanName] ?: ""

        // Устанавливаем название (без эмодзи)
        holder.binding.tvSportName.text = cleanName

        // Количество мест
        holder.binding.tvCount.text = "$placesCount мест"

        // Устанавливаем цвет карточки
        val cardView = holder.binding.cardView as MaterialCardView
        cardView.setCardBackgroundColor(Color.parseColor(sportType.color))

        holder.itemView.setOnClickListener { onItemClick(sportType) }
    }

    override fun getItemCount() = sportTypes.size
}