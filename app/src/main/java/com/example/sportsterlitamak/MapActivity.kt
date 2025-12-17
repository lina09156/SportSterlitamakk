package com.example.sportsterlitamak

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View  // ДОБАВЬТЕ ЭТОТ ИМПОРТ!
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sportsterlitamak.databinding.ActivityMapBinding
import com.example.sportsterlitamak.databinding.ItemFilterBinding
import com.google.android.material.card.MaterialCardView
import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.map.MapObjectCollection
import com.yandex.mapkit.mapview.MapView
import com.yandex.mapkit.user_location.UserLocationLayer
import com.yandex.runtime.image.ImageProvider
import com.yandex.runtime.ui_view.ViewProvider

class MapActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMapBinding
    private lateinit var mapView: MapView
    private lateinit var mapObjects: MapObjectCollection
    private var userLocationLayer: UserLocationLayer? = null

    // Координаты центра Стерлитамака
    private val sterlitamakCenter = Point(53.6306, 55.9506)

    // Выбранные фильтры
    private val selectedSportTypes = mutableSetOf<Int>()

    companion object {
        private const val LOCATION_PERMISSION_REQUEST = 100

        // Цвета для разных видов спорта
        private val sportColors = mapOf(
            1 to Color.parseColor("#4CAF50"), // Футбол - зеленый
            2 to Color.parseColor("#FF5722"), // Баскетбол - оранжевый
            3 to Color.parseColor("#2196F3"), // Теннис - синий
            4 to Color.parseColor("#00BCD4"), // Плавание - голубой
            5 to Color.parseColor("#F44336"), // Единоборства - красный
            6 to Color.parseColor("#9C27B0"), // Фитнес - фиолетовый
            7 to Color.parseColor("#3F51B5"), // Хоккей - темно-синий
            8 to Color.parseColor("#FF9800")  // Волейбол - желтый
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Инициализация Яндекс Карт ДО setContentView
        // ЗАМЕНИТЕ НА ВАШ КЛЮЧ!
        MapKitFactory.setApiKey("d17517b1-6e6f-4ac6-978c-487a1e0120e8")
        MapKitFactory.initialize(this)

        binding = ActivityMapBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mapView = binding.mapView
        mapObjects = mapView.map.mapObjects.addCollection()

        setupMap()
        setupUI()
        addAllSportPlacesToMap()
        setupFilter()
    }

    private fun setupMap() {
        // Устанавливаем камеру на Стерлитамак
        mapView.map.move(
            CameraPosition(sterlitamakCenter, 12.0f, 0.0f, 0.0f),
            Animation(Animation.Type.SMOOTH, 1f),
            null
        )

        // Включаем жесты
        mapView.map.isRotateGesturesEnabled = true
        mapView.map.isZoomGesturesEnabled = true
        mapView.map.isScrollGesturesEnabled = true

        // Проверяем разрешение на местоположение
        if (checkLocationPermission()) {
            setupUserLocation()
        }
    }

    private fun setupUI() {
        // Кнопка назад
        binding.btnBack.setOnClickListener {
            finish()
        }

        // Кнопка фильтра
        binding.btnFilter.setOnClickListener {
            val isVisible = binding.cardFilter.visibility == View.VISIBLE
            binding.cardFilter.visibility = if (isVisible) View.GONE else View.VISIBLE
        }

        // Кнопка текущего местоположения
        binding.fabMyLocation.setOnClickListener {
            if (checkLocationPermission()) {
                setupUserLocation()
                userLocationLayer?.isVisible = true
                userLocationLayer?.isHeadingEnabled = true
            } else {
                requestLocationPermission()
            }
        }
    }

    private fun setupFilter() {
        binding.rvFilter.layoutManager = LinearLayoutManager(this)
        binding.rvFilter.adapter = FilterAdapter(DataSource.sportTypes) { sportTypeId, isChecked ->
            if (isChecked) {
                selectedSportTypes.add(sportTypeId)
            } else {
                selectedSportTypes.remove(sportTypeId)
            }
        }

        // Кнопка применения фильтра
        binding.btnApplyFilter.setOnClickListener {
            binding.cardFilter.visibility = View.GONE
            filterMapBySportTypes()
        }
    }

    private fun filterMapBySportTypes() {
        // Очищаем карту
        mapObjects.clear()

        // Если ничего не выбрано - показываем все
        val placesToShow = if (selectedSportTypes.isEmpty()) {
            DataSource.sportPlaces
        } else {
            DataSource.sportPlaces.filter { selectedSportTypes.contains(it.sportTypeId) }
        }

        // Добавляем отфильтрованные места
        placesToShow.forEach { place ->
            addPlaceToMap(place)
        }

        // Обновляем заголовок
        val count = placesToShow.size
        binding.tvTitle.text = "Найдено мест: $count"
    }

    private fun addAllSportPlacesToMap() {
        DataSource.sportPlaces.forEach { place ->
            addPlaceToMap(place)
        }
    }

    private fun addPlaceToMap(place: SportPlace) {
        val point = Point(place.latitude, place.longitude)

        // Создаем метку
        val placemark = mapObjects.addPlacemark(point)

        // Устанавливаем цвет в зависимости от вида спорта
        val color = sportColors[place.sportTypeId] ?: Color.GRAY

        // Используем стандартную метку
        // placemark.setIcon(ImageProvider.fromResource(this, R.drawable.ic_map_marker))

        // Устанавливаем балун
        placemark.userData = place
        placemark.addTapListener { mapObject, point ->
            val clickedPlace = mapObject.userData as SportPlace
            showPlaceInfo(clickedPlace)
            true
        }
    }

    private fun showPlaceInfo(place: SportPlace) {
        // Показываем Toast с информацией
        Toast.makeText(this,
            "${place.name}\n${place.address}\n${place.priceInfo}",
            Toast.LENGTH_LONG
        ).show()

        // Или открываем детальную информацию
        val intent = Intent(this, PlaceDetailActivity::class.java)
        intent.putExtra("PLACE_ID", place.id)
        startActivity(intent)
    }

    private fun setupUserLocation() {
        val mapKit = MapKitFactory.getInstance()
        userLocationLayer = mapKit.createUserLocationLayer(mapView.mapWindow)
        userLocationLayer?.isVisible = true
        userLocationLayer?.isHeadingEnabled = true
    }

    private fun checkLocationPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestLocationPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ),
            LOCATION_PERMISSION_REQUEST
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == LOCATION_PERMISSION_REQUEST) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                setupUserLocation()
            } else {
                Toast.makeText(this, "Разрешите доступ к местоположению", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Адаптер для фильтров
    class FilterAdapter(
        private val sportTypes: List<SportType>,
        private val onCheckedChange: (Int, Boolean) -> Unit
    ) : RecyclerView.Adapter<FilterAdapter.ViewHolder>() {

        class ViewHolder(val binding: ItemFilterBinding) : RecyclerView.ViewHolder(binding.root)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val binding = ItemFilterBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
            return ViewHolder(binding)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val sportType = sportTypes[position]
            val placesCount = DataSource.getPlacesBySportType(sportType.id).size

            holder.binding.tvSportName.text = sportType.name
            holder.binding.tvCount.text = placesCount.toString()

            holder.binding.cbSportType.setOnCheckedChangeListener(null) // Сбрасываем предыдущий listener
            holder.binding.cbSportType.setOnCheckedChangeListener { _, isChecked ->
                onCheckedChange(sportType.id, isChecked)
            }
        }

        override fun getItemCount() = sportTypes.size
    }

    // Жизненный цикл Яндекс Карт
    override fun onStart() {
        super.onStart()
        MapKitFactory.getInstance().onStart()
        mapView.onStart()
    }

    override fun onStop() {
        mapView.onStop()
        MapKitFactory.getInstance().onStop()
        super.onStop()
    }
}