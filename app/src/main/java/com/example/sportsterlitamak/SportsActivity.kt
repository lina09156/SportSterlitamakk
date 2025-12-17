package com.example.sportsterlitamak

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.example.sportsterlitamak.databinding.ActivitySportsBinding
import com.google.android.material.card.MaterialCardView

class SportsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySportsBinding
    private lateinit var adapter: SportTypeAdapter
    private val snapHelper: SnapHelper = PagerSnapHelper()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySportsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        setupDotsIndicator()
        setupButton()
    }

    private fun setupRecyclerView() {
        // Горизонтальный layout
        binding.rvSports.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.HORIZONTAL,
            false
        )

        // Для эффекта "страниц" при прокрутке
        snapHelper.attachToRecyclerView(binding.rvSports)

        adapter = SportTypeAdapter(DataSource.sportTypes) { sportType ->
            // Переход к местам этого вида спорта
            val intent = Intent(this, PlacesActivity::class.java)
            intent.putExtra("SPORT_TYPE_ID", sportType.id)
            intent.putExtra("SPORT_NAME", sportType.name)
            intent.putExtra("SPORT_COLOR", sportType.color)
            startActivity(intent)
        }

        binding.rvSports.adapter = adapter

        // Слушатель прокрутки для обновления индикатора
        binding.rvSports.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                updateDotsIndicator()
            }
        })
    }

    private fun setupDotsIndicator() {
        binding.dotsLayout.removeAllViews()

        for (i in 0 until DataSource.sportTypes.size) {
            val dot = ImageView(this)
            dot.setImageResource(android.R.drawable.presence_invisible)
            dot.setColorFilter(Color.WHITE)

            val params = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(8, 0, 8, 0)
            dot.layoutParams = params

            binding.dotsLayout.addView(dot)
        }

        updateDotsIndicator()
    }

    private fun updateDotsIndicator() {
        val layoutManager = binding.rvSports.layoutManager as LinearLayoutManager
        val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

        for (i in 0 until binding.dotsLayout.childCount) {
            val dot = binding.dotsLayout.getChildAt(i) as ImageView
            if (i == firstVisibleItemPosition) {
                dot.setImageResource(android.R.drawable.presence_online)
            } else {
                dot.setImageResource(android.R.drawable.presence_invisible)
            }
        }
    }

    private fun setupButton() {
        // Кнопка "Карта всех мест"
        binding.btnOpenMap.setOnClickListener {
            val intent = Intent(this, MapActivity::class.java)
            startActivity(intent)
        }

        // Кнопка "Список всех мест"
        binding.btnAllPlaces.setOnClickListener {
            // Переходим на PlacesActivity с фильтром "все места"
            val intent = Intent(this, PlacesActivity::class.java)
            intent.putExtra("SPORT_TYPE_ID", 0) // 0 = все виды спорта
            intent.putExtra("SPORT_NAME", "Все виды спорта")
            intent.putExtra("SPORT_COLOR", "#607D8B")
            startActivity(intent)
        }
    }
}