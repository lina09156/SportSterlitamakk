package com.example.sportsterlitamak

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sportsterlitamak.databinding.ActivityPlacesBinding
import kotlinx.coroutines.*

class PlacesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPlacesBinding
    private var sportTypeId: Int = 0
    private var sportName: String = ""
    private var sportColor: String = ""
    private lateinit var adapter: SportPlaceAdapter
    private var allPlaces = listOf<SportPlace>()
    private var filteredPlaces = listOf<SportPlace>()
    private var searchJob: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPlacesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Получаем данные о виде спорта
        sportTypeId = intent.getIntExtra("SPORT_TYPE_ID", 0)
        sportName = intent.getStringExtra("SPORT_NAME") ?: ""
        sportColor = intent.getStringExtra("SPORT_COLOR") ?: "#4CAF50"

        setupUI()
        setupSearch()
        setupRecyclerView()
    }

    private fun setupUI() {
        // Если sportTypeId == 0, значит это "Все виды спорта"
        binding.tvSportTitle.text = if (sportTypeId == 0) {
            "Все спортивные места"
        } else {
            sportName
        }

        binding.ivBack.setOnClickListener {
            finish()
        }

        // Показываем/скрываем поле поиска
        if (sportTypeId == 0) {
            binding.searchContainer.visibility = android.view.View.VISIBLE
        } else {
            binding.searchContainer.visibility = android.view.View.GONE
        }
    }

    private fun setupSearch() {
        // Настройка слушателя текста для поиска
        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Показываем/скрываем кнопку очистки
                binding.ivClearSearch.visibility = if (s.isNullOrEmpty()) {
                    android.view.View.GONE
                } else {
                    android.view.View.VISIBLE
                }

                // Запускаем поиск с задержкой
                searchJob?.cancel()
                searchJob = CoroutineScope(Dispatchers.Main).launch {
                    delay(300) // Задержка 300 мс для уменьшения количества поисков
                    performSearch(s.toString())
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        // Обработка кнопки очистки поиска
        binding.ivClearSearch.setOnClickListener {
            binding.etSearch.text.clear()
            binding.etSearch.clearFocus()
            hideKeyboard()
        }

        // Обработка нажатия Enter на клавиатуре
        binding.etSearch.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH || actionId == EditorInfo.IME_ACTION_DONE) {
                performSearch(binding.etSearch.text.toString())
                hideKeyboard()
                true
            } else {
                false
            }
        }

        // Обработка клика на иконку поиска
        binding.ivSearchIcon.setOnClickListener {
            performSearch(binding.etSearch.text.toString())
            hideKeyboard()
        }
    }

    private fun setupRecyclerView() {
        // Получаем места для этого вида спорта
        allPlaces = if (sportTypeId == 0) {
            DataSource.sportPlaces
        } else {
            DataSource.getPlacesBySportType(sportTypeId)
        }

        filteredPlaces = allPlaces

        binding.rvPlaces.layoutManager = LinearLayoutManager(this)

        adapter = SportPlaceAdapter(filteredPlaces) { place ->
            // Переход к детальной информации
            val intent = Intent(this, PlaceDetailActivity::class.java)
            intent.putExtra("PLACE_ID", place.id)
            startActivity(intent)
        }

        binding.rvPlaces.adapter = adapter
    }

    private fun performSearch(query: String) {
        val searchQuery = query.trim()

        if (searchQuery.isEmpty()) {
            // Если поисковый запрос пустой, показываем все места
            filteredPlaces = allPlaces
            updateRecyclerView()
            return
        }

        // Выполняем поиск в фоновом потоке
        CoroutineScope(Dispatchers.Default).launch {
            val results = DataSource.searchPlaces(searchQuery)

            // Фильтруем по типу спорта, если не в режиме "Все места"
            val filteredResults = if (sportTypeId != 0) {
                results.filter { it.sportTypeId == sportTypeId }
            } else {
                results
            }

            withContext(Dispatchers.Main) {
                filteredPlaces = filteredResults
                updateRecyclerView()

                // Показываем/скрываем сообщение об отсутствии результатов
                if (filteredPlaces.isEmpty() && searchQuery.isNotEmpty()) {
                    binding.tvEmptySearch.visibility = android.view.View.VISIBLE
                    binding.rvPlaces.visibility = android.view.View.GONE
                } else {
                    binding.tvEmptySearch.visibility = android.view.View.GONE
                    binding.rvPlaces.visibility = android.view.View.VISIBLE
                }
            }
        }
    }

    private fun updateRecyclerView() {
        adapter.updatePlaces(filteredPlaces)
    }

    private fun hideKeyboard() {
        val inputMethodManager = getSystemService(android.content.Context.INPUT_METHOD_SERVICE)
                as android.view.inputmethod.InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(binding.etSearch.windowToken, 0)
    }

    override fun onDestroy() {
        super.onDestroy()
        searchJob?.cancel()
    }
}