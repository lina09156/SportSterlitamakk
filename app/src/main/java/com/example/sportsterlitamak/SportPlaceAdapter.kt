package com.example.sportsterlitamak

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.sportsterlitamak.databinding.ItemSportPlaceBinding
import java.util.Locale

class SportPlaceAdapter(
    private var places: List<SportPlace>,
    private val onItemClick: (SportPlace) -> Unit
) : RecyclerView.Adapter<SportPlaceAdapter.ViewHolder>() {

    private var searchQuery: String = ""
    private var originalPlaces: List<SportPlace> = places
    private var currentContext: android.content.Context? = null

    class ViewHolder(val binding: ItemSportPlaceBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        currentContext = parent.context
        val binding = ItemSportPlaceBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val place = places[position]

        // Устанавливаем текст с подсветкой поиска
        holder.binding.tvPlaceName.text = highlightText(place.name, searchQuery, currentContext)
        holder.binding.tvAddress.text = highlightText(place.address, searchQuery, currentContext)
        holder.binding.tvPrice.text = place.priceInfo.split("\n").firstOrNull() ?: place.priceInfo
        holder.binding.ratingBar.rating = place.rating

        // Отображаем числовое значение рейтинга
        holder.binding.tvRatingValue.text = String.format(Locale.getDefault(), "%.1f", place.rating)

        // Устанавливаем цвет типа спорта
        val sportColor = getSportColor(place.sportTypeId, holder.itemView.context)
        holder.binding.cardView.setCardBackgroundColor(sportColor)

        // Добавляем значок "найдено по поиску" если есть поисковый запрос
        if (searchQuery.isNotEmpty() &&
            (place.name.contains(searchQuery, true) ||
                    place.address.contains(searchQuery, true) ||
                    place.description.contains(searchQuery, true))) {
            holder.binding.ivSearchMatch.visibility = android.view.View.VISIBLE
        } else {
            holder.binding.ivSearchMatch.visibility = android.view.View.GONE
        }

        // Уменьшаем шрифт для тренажерного зала
        if (place.sportTypeId == 11) {
            // Получаем название из DataSource.sportTypes
            val sportType = DataSource.sportTypes.find { it.id == 11 }
            if (sportType?.name?.contains("Тренажерный") == true) {
                // Устанавливаем меньший шрифт для текста названия места
                holder.binding.tvPlaceName.textSize = 16f // было 18sp в XML
                holder.binding.tvAddress.textSize = 12f   // было 14sp в XML
            }
        } else {
            // Возвращаем стандартный размер шрифта
            holder.binding.tvPlaceName.textSize = 18f
            holder.binding.tvAddress.textSize = 14f
        }

        holder.binding.root.setOnClickListener { onItemClick(place) }
    }

    override fun getItemCount() = places.size

    // Функция для обновления списка мест (используется при поиске)
    fun updatePlaces(newPlaces: List<SportPlace>) {
        places = newPlaces
        notifyDataSetChanged()
    }

    // Функция для фильтрации по поисковому запросу
    fun filter(query: String) {
        searchQuery = query
        if (query.isEmpty()) {
            updatePlaces(originalPlaces)
            return
        }

        val filtered = originalPlaces.filter { place ->
            place.name.contains(query, true) ||
                    place.address.contains(query, true) ||
                    place.description.contains(query, true)
        }

        updatePlaces(filtered)
    }

    // Функция для обновления исходного списка (при смене категории)
    fun updateOriginalPlaces(newPlaces: List<SportPlace>) {
        originalPlaces = newPlaces
        places = newPlaces
        searchQuery = ""
        notifyDataSetChanged()
    }

    // Функция для подсветки текста поиска (исправленная версия)
    private fun highlightText(text: String, query: String, context: android.content.Context?): CharSequence {
        if (query.isEmpty() || context == null) return text

        return android.text.SpannableString(text).apply {
            val lowerText = text.lowercase(Locale.getDefault())
            val lowerQuery = query.lowercase(Locale.getDefault())

            var startIndex = lowerText.indexOf(lowerQuery)
            while (startIndex >= 0) {
                val endIndex = startIndex + query.length
                val highlightColor = ContextCompat.getColor(
                    context,
                    android.R.color.holo_orange_light
                )
                val span = android.text.style.ForegroundColorSpan(highlightColor)
                setSpan(span, startIndex, endIndex, android.text.Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

                // Ищем следующее вхождение
                startIndex = lowerText.indexOf(lowerQuery, endIndex)
            }
        }
    }

    // Функция для получения цвета по типу спорта
    private fun getSportColor(sportTypeId: Int, context: android.content.Context): Int {
        val colorHex = when (sportTypeId) {
            1 -> "#4CAF50"  // Футбол - зеленый
            2 -> "#FF5722"  // Баскетбол - оранжевый
            3 -> "#2196F3"  // Теннис - синий
            4 -> "#00BCD4"  // Плавание - голубой
            5 -> "#F44336"  // Единоборства - красный
            6 -> "#9C27B0"  // Фитнес - фиолетовый
            7 -> "#3F51B5"  // Хоккей - индиго
            8 -> "#FF9800"  // Волейбол - янтарный
            9 -> "#8BC34A"  // Йога - светлый зеленый (ваш цвет)
            10 -> "#795548" // Бокс - коричневый
            11 -> "#607D8B" // Тренажерный зал - сине-серый
            else -> "#757575" // По умолчанию - серый
        }

        return android.graphics.Color.parseColor(colorHex)
    }
}