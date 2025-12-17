package com.example.sportsterlitamak

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sportsterlitamak.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        binding.recyclerView.adapter = SportPlaceAdapter(DataSource.sportPlaces) { selectedPlace ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("PLACE_ID", selectedPlace.id)
            startActivity(intent)
        }
    }
}