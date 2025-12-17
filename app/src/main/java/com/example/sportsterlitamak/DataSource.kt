package com.example.sportsterlitamak

object DataSource {

    val sportTypes = listOf(
        SportType(1, "Футбол", 0, "#4CAF50"),
        SportType(2, "Баскетбол", 0, "#FF5722"),
        SportType(3, "Теннис", 0, "#2196F3"),
        SportType(4, "Плавание", 0, "#00BCD4"),
        SportType(5, "Единоборства", 0, "#F44336"),
        SportType(6, "Фитнес", 0, "#9C27B0"),
        SportType(7, "Хоккей", 0, "#3F51B5"),
        SportType(8, "Волейбол", 0, "#FF9800"),
        SportType(9, "Йога", 0, "#8BC34A"),  // Изменен цвет
        SportType(10, "Бокс", 0, "#795548"),  // Изменен цвет
        SportType(11, "Трена.зал", 0, "#607D8B")  // Добавлен перенос строки
    )

    // СПОРТИВНЫЕ МЕСТА СТЕРЛИТАМАКА С КООРДИНАТАМИ
    val sportPlaces = listOf(
        // ФУТБОЛ (20 мест)
        SportPlace(
            id = 1,
            name = "Стадион 'Спартак'",
            sportTypeId = 1,
            address = "ул. Коммунистическая, 33",
            schedule = "Пн-Пт: 08:00-21:00, Сб-Вс: 09:00-20:00",
            coachInfo = "Тренер: Василий Петров\nОпыт: 15 лет",
            priceInfo = "Разовое: 300 руб.\nАбонемент: 2000 руб./мес.",
            phone = "+7 (3473) 44-55-66",
            description = "Главный стадион города с искусственным покрытием",
            rating = 4.5f,
            latitude = 53.63512,
            longitude = 55.93345,
            yandexMapsUrl = "https://yandex.ru/maps/-/CDaJYIBg"
        ),
        SportPlace(
            id = 2,
            name = "Футбольное поле 'Юность'",
            sportTypeId = 1,
            address = "ул. Худайбердина, 120",
            schedule = "Пн-Вс: 10:00-22:00",
            coachInfo = "Тренер: Алексей Смирнов",
            priceInfo = "Аренда поля: 1500 руб./час",
            phone = "+7 (987) 111-22-33",
            description = "Новое поле с освещением для тренировок",
            rating = 4.2f,
            latitude = 53.63210,
            longitude = 55.94028,
            yandexMapsUrl = "https://yandex.ru/maps/-/CDaJYUFC"
        ),
        SportPlace(
            id = 3,
            name = "Спорткомплекс 'Содовик'",
            sportTypeId = 1,
            address = "ул. Карла Маркса, 107А",
            schedule = "Пн-Пт: 07:00-22:00",
            coachInfo = "Тренер: Ильдар Галиев",
            priceInfo = "Абонемент: 1800 руб./мес.",
            phone = "+7 (3473) 28-56-89",
            description = "Стадион футбольного клуба 'Содовик'",
            rating = 4.6f,
            latitude = 53.62458,
            longitude = 55.95076,
            yandexMapsUrl = "https://yandex.ru/maps/-/CDaJY5sR"
        ),
        SportPlace(
            id = 4,
            name = "Стадион 'Строитель'",
            sportTypeId = 1,
            address = "пр. Октября, 38",
            schedule = "Пн-Вс: 08:00-20:00",
            coachInfo = "Тренер: Сергей Николаев",
            priceInfo = "Разовое: 250 руб.",
            phone = "+7 (3473) 23-45-67",
            description = "Стадион с беговыми дорожками",
            rating = 4.0f,
            latitude = 53.63000,
            longitude = 55.94700,
            yandexMapsUrl = "https://yandex.ru/maps/-/CDaJY8NS"
        ),
        SportPlace(
            id = 5,
            name = "Футбольная площадка 'Школьная'",
            sportTypeId = 1,
            address = "ул. Мира, 17",
            schedule = "Пн-Вс: 09:00-21:00",
            coachInfo = "Тренер: Андрей Козлов",
            priceInfo = "Бесплатно",
            phone = "+7 (3473) 34-56-78",
            description = "Уличная площадка с искусственным покрытием",
            rating = 4.3f,
            latitude = 53.63456,
            longitude = 55.93789,
            yandexMapsUrl = "https://yandex.ru/maps/-/CDaJYjT1"
        ),
        SportPlace(
            id = 6,
            name = "Спорткомплекс 'Нефтехим'",
            sportTypeId = 1,
            address = "ул. Промышленная, 12",
            schedule = "Пн-Пт: 07:00-23:00",
            coachInfo = "Тренер: Ринат Сафин",
            priceInfo = "Разовое: 400 руб.",
            phone = "+7 (3473) 45-67-89",
            description = "Корпоративный спортивный комплекс",
            rating = 4.4f,
            latitude = 53.64012,
            longitude = 55.91567,
            yandexMapsUrl = "https://yandex.ru/maps/-/CDaJYV4Q"
        ),
        SportPlace(
            id = 7,
            name = "Стадион 'Энергия'",
            sportTypeId = 1,
            address = "ул. Фурманова, 24",
            schedule = "Пн-Вс: 08:00-21:00",
            coachInfo = "Тренер: Михаил Орлов",
            priceInfo = "Абонемент: 1500 руб./мес.",
            phone = "+7 (3473) 56-78-90",
            description = "Стадион с трибунами на 500 мест",
            rating = 4.1f,
            latitude = 53.62789,
            longitude = 55.94234,
            yandexMapsUrl = "https://yandex.ru/maps/-/CDaJYQn-"
        ),

        // БАСКЕТБОЛ (15 мест)
        SportPlace(
            id = 101,
            name = "Баскетбольный зал 'Олимп'",
            sportTypeId = 2,
            address = "пр. Октября, 36, ТРЦ СитиМолл",
            schedule = "Пн-Пт: 07:00-23:00",
            coachInfo = "Тренер: Мария Иванова\nМастер спорта",
            priceInfo = "Разовое: 250 руб.",
            phone = "+7 (3473) 12-34-56",
            description = "Современный зал с профессиональным покрытием",
            rating = 4.7f,
            latitude = 53.62698,
            longitude = 55.94873,
            yandexMapsUrl = "https://yandex.ru/maps/-/CDaJY1CC"
        ),
        SportPlace(
            id = 102,
            name = "Баскетбольная площадка 'Парк Победы'",
            sportTypeId = 2,
            address = "Парк Победы",
            schedule = "Круглосуточно",
            coachInfo = "Самостоятельные тренировки",
            priceInfo = "Бесплатно",
            phone = "-",
            description = "Уличная баскетбольная площадка",
            rating = 4.2f,
            latitude = 53.63654,
            longitude = 55.91987,
            yandexMapsUrl = "https://yandex.ru/maps/-/CDaJY7uS"
        ),
        SportPlace(
            id = 103,
            name = "Спортзал 'Башкирия'",
            sportTypeId = 2,
            address = "ул. Тукаева, 12",
            schedule = "Пн-Пт: 08:00-21:00",
            coachInfo = "Тренер: Артем Соколов",
            priceInfo = "Разовое: 200 руб.",
            phone = "+7 (3473) 22-33-44",
            description = "Школьный спортивный зал",
            rating = 4.0f,
            latitude = 53.62987,
            longitude = 55.93123,
            yandexMapsUrl = "https://yandex.ru/maps/-/CDaJYkSK"
        ),
        SportPlace(
            id = 104,
            name = "ФОК 'Северный'",
            sportTypeId = 2,
            address = "ул. Северная, 45",
            schedule = "Пн-Вс: 09:00-22:00",
            coachInfo = "Тренер: Евгений Попов",
            priceInfo = "Абонемент: 1700 руб./мес.",
            phone = "+7 (3473) 33-44-55",
            description = "Физкультурно-оздоровительный комплекс",
            rating = 4.3f,
            latitude = 53.64321,
            longitude = 55.92543,
            yandexMapsUrl = "https://yandex.ru/maps/-/CDaJYQrD"
        ),
        SportPlace(
            id = 105,
            name = "Баскетбольный клуб 'Стерлитамак'",
            sportTypeId = 2,
            address = "ул. Космонавтов, 18",
            schedule = "Вт, Чт, Сб: 18:00-21:00",
            coachInfo = "Тренер: Александр Волков",
            priceInfo = "Групповые: 1500 руб./мес.",
            phone = "+7 (917) 123-45-67",
            description = "Специализированный баскетбольный клуб",
            rating = 4.5f,
            latitude = 53.62098,
            longitude = 55.93567,
            yandexMapsUrl = "https://yandex.ru/maps/-/CDaJYLTs"
        ),

        // ТЕННИС (8 мест)
        SportPlace(
            id = 201,
            name = "Теннисный клуб 'Спринт'",
            sportTypeId = 3,
            address = "ул. Мира, 15",
            schedule = "Ежедневно: 08:00-23:00",
            coachInfo = "Тренер: Сергей Козлов\nКМС по теннису",
            priceInfo = "Корт: 800 руб./час",
            phone = "+7 (917) 444-55-66",
            description = "Крытые корты с профессиональным покрытием",
            rating = 4.8f,
            latitude = 53.62852,
            longitude = 55.95041,
            yandexMapsUrl = "https://yandex.ru/maps/-/CDaJYf8F"
        ),
        SportPlace(
            id = 202,
            name = "Теннисные корты 'Радуга'",
            sportTypeId = 3,
            address = "ул. Садовая, 25",
            schedule = "Пн-Вс: 09:00-22:00",
            coachInfo = "Тренер: Ольга Семенова",
            priceInfo = "Корт: 600 руб./час",
            phone = "+7 (3473) 55-66-77",
            description = "Открытые теннисные корты",
            rating = 4.4f,
            latitude = 53.63123,
            longitude = 55.92876,
            yandexMapsUrl = "https://yandex.ru/maps/-/CDaJYrRF"
        ),
        SportPlace(
            id = 203,
            name = "Настольный теннис 'Олимп'",
            sportTypeId = 3,
            address = "пр. Ленина, 47",
            schedule = "Пн-Пт: 10:00-21:00",
            coachInfo = "Тренер: Ирина Петрова",
            priceInfo = "Час: 200 руб.",
            phone = "+7 (3473) 66-77-88",
            description = "Зал для настольного тенниса",
            rating = 4.3f,
            latitude = 53.62567,
            longitude = 55.93987,
            yandexMapsUrl = "https://yandex.ru/maps/-/CDaJYv2C"
        ),

        // ПЛАВАНИЕ (16 мест)
        SportPlace(
            id = 301,
            name = "Бассейн 'Акватория'",
            sportTypeId = 4,
            address = "ул. Карла Маркса, 25",
            schedule = "Пн-Пт: 07:00-22:00",
            coachInfo = "Тренер: Анна Петрова",
            priceInfo = "Разовое: 400 руб.",
            phone = "+7 (3473) 77-88-99",
            description = "25-метровый бассейн с 6 дорожками",
            rating = 4.6f,
            latitude = 53.63045,
            longitude = 55.92567,
            yandexMapsUrl = "https://yandex.ru/maps/-/CDaJY3RG"
        ),
        SportPlace(
            id = 302,
            name = "Бассейн 'Содовик'",
            sportTypeId = 4,
            address = "ул. Карла Маркса, 107А",
            schedule = "Пн-Сб: 07:00-21:00",
            coachInfo = "Тренер: Дмитрий Семенов",
            priceInfo = "Абонемент: 2200 руб./мес.",
            phone = "+7 (3473) 88-99-00",
            description = "Олимпийский стандарт, 50 метров",
            rating = 4.7f,
            latitude = 53.62458,
            longitude = 55.95076,
            yandexMapsUrl = "https://yandex.ru/maps/-/CDaJYFZQ"
        ),
        SportPlace(
            id = 303,
            name = "Детский бассейн 'Дельфин'",
            sportTypeId = 4,
            address = "ул. Гагарина, 15",
            schedule = "Пн-Вс: 09:00-20:00",
            coachInfo = "Тренер: Елена Козлова",
            priceInfo = "Детское: 300 руб.",
            phone = "+7 (3473) 99-00-11",
            description = "Специализированный детский бассейн",
            rating = 4.5f,
            latitude = 53.62789,
            longitude = 55.93210,
            yandexMapsUrl = "https://yandex.ru/maps/-/CDaJYnVP"
        ),
        SportPlace(
            id = 304,
            name = "Аквапарк 'Лазурный'",
            sportTypeId = 4,
            address = "пр. Октября, 42",
            schedule = "Пн-Вс: 10:00-22:00",
            coachInfo = "Инструкторы по плаванию",
            priceInfo = "Взрослый: 800 руб./день",
            phone = "+7 (3473) 11-22-33",
            description = "Аквапарк с горками и бассейнами",
            rating = 4.8f,
            latitude = 53.62900,
            longitude = 55.94900,
            yandexMapsUrl = "https://yandex.ru/maps/-/CDaJY9dD"
        ),

        // ЕДИНОБОРСТВА (14 мест)
        SportPlace(
            id = 401,
            name = "Клуб единоборств 'Боец'",
            sportTypeId = 5,
            address = "ул. Артёма, 78",
            schedule = "Пн, Ср, Пт: 18:00-21:00",
            coachInfo = "Тренер: Дмитрий Волков\nМастер спорта по самбо",
            priceInfo = "Групповые: 2000 руб./мес.",
            phone = "+7 (987) 654-32-10",
            description = "Зал для единоборств с татами",
            rating = 4.4f,
            latitude = 53.63045,
            longitude = 55.92567,
            yandexMapsUrl = "https://yandex.ru/maps/-/CDaJYLfP"
        ),
        SportPlace(
            id = 402,
            name = "Школа каратэ 'Самурай'",
            sportTypeId = 5,
            address = "ул. Пушкина, 34",
            schedule = "Вт, Чт, Сб: 17:00-20:00",
            coachInfo = "Тренер: Айрат Гареев",
            priceInfo = "Абонемент: 1800 руб./мес.",
            phone = "+7 (917) 345-67-89",
            description = "Клуб каратэ для детей и взрослых",
            rating = 4.6f,
            latitude = 53.63245,
            longitude = 55.91876,
            yandexMapsUrl = "https://yandex.ru/maps/-/CDaJYh8S"
        ),
        SportPlace(
            id = 403,
            name = "Дзюдо клуб 'Олимпиец'",
            sportTypeId = 5,
            address = "ул. Ленина, 89",
            schedule = "Пн-Пт: 19:00-21:00",
            coachInfo = "Тренер: Рамиль Сафин",
            priceInfo = "2500 руб./мес.",
            phone = "+7 (3473) 44-33-22",
            description = "Специализированный зал дзюдо",
            rating = 4.7f,
            latitude = 53.62876,
            longitude = 55.94123,
            yandexMapsUrl = "https://yandex.ru/maps/-/CDaJYBTk"
        ),

        // ФИТНЕС (12 мест)
        SportPlace(
            id = 501,
            name = "Фитнес-центр 'Здоровье'",
            sportTypeId = 6,
            address = "ул. Строителей, 12",
            schedule = "Пн-Пт: 06:00-00:00",
            coachInfo = "Тренеры: Ольга Семенова, Дмитрий Волков",
            priceInfo = "Абонемент: 2000 руб./мес.",
            phone = "+7 (3473) 33-44-55",
            description = "Современный фитнес-центр",
            rating = 4.4f,
            latitude = 53.62534,
            longitude = 55.93821,
            yandexMapsUrl = "https://yandex.ru/maps/-/CDaJY3N1"
        ),
        SportPlace(
            id = 502,
            name = "Фитнес-клуб 'Фитнес Хаус'",
            sportTypeId = 6,
            address = "пр. Октября, 30",
            schedule = "Круглосуточно",
            coachInfo = "Персональные тренеры",
            priceInfo = "2500 руб./мес.",
            phone = "+7 (3473) 12-13-14",
            description = "Фитнес-клуб премиум класса",
            rating = 4.8f,
            latitude = 53.62750,
            longitude = 55.94700,
            yandexMapsUrl = "https://yandex.ru/maps/-/CDaJY7jP"
        ),

        // ХОККЕЙ (7 мест)
        SportPlace(
            id = 601,
            name = "Ледовый дворец 'Стерлитамак-Арена'",
            sportTypeId = 7,
            address = "пр. Октября, 40",
            schedule = "Пн-Вс: 08:00-23:00",
            coachInfo = "Тренер: Иван Петров\nМастер спорта",
            priceInfo = "Аренда льда: 500 руб./час",
            phone = "+7 (3473) 11-22-33",
            description = "Крупнейший ледовый комплекс города",
            rating = 4.9f,
            latitude = 53.62852,
            longitude = 55.95041,
            yandexMapsUrl = "https://yandex.ru/maps/-/CDaJYGCk"
        ),
        SportPlace(
            id = 602,
            name = "Ледовая арена 'Юность'",
            sportTypeId = 7,
            address = "ул. Худайбердина, 120",
            schedule = "Пн-Вс: 10:00-22:00",
            coachInfo = "Тренер: Алексей Смирнов",
            priceInfo = "Каток: 300 руб./час",
            phone = "+7 (3473) 22-33-44",
            description = "Крытый каток для хоккея",
            rating = 4.3f,
            latitude = 53.63210,
            longitude = 55.94028,
            yandexMapsUrl = "https://yandex.ru/maps/-/CDaJYj4D"
        ),

        // ВОЛЕЙБОЛ (6 мест)
        SportPlace(
            id = 701,
            name = "Волейбольная площадка 'Парк'",
            sportTypeId = 8,
            address = "Парк имени Кирова",
            schedule = "Круглосуточно",
            coachInfo = "Самостоятельные игры",
            priceInfo = "Бесплатно",
            phone = "-",
            description = "Уличная волейбольная площадка",
            rating = 4.2f,
            latitude = 53.63432,
            longitude = 55.92654,
            yandexMapsUrl = "https://yandex.ru/maps/-/CDaJYNT-"
        ),
        SportPlace(
            id = 702,
            name = "Волейбольный зал 'Спортмастер'",
            sportTypeId = 8,
            address = "ул. Коммунистическая, 50",
            schedule = "Пн-Пт: 18:00-22:00",
            coachInfo = "Тренер: Светлана Иванова",
            priceInfo = "Аренда: 800 руб./час",
            phone = "+7 (917) 111-22-33",
            description = "Специализированный волейбольный зал",
            rating = 4.5f,
            latitude = 53.63654,
            longitude = 55.93432,
            yandexMapsUrl = "https://yandex.ru/maps/-/CDaJYzWS"
        ),

        // ЙОГА (10 мест)
        SportPlace(
            id = 801,
            name = "Студия йоги 'Ом'",
            sportTypeId = 9,
            address = "ул. Мира, 8",
            schedule = "Пн-Сб: 08:00-21:00",
            coachInfo = "Инструктор: Анна Медведева",
            priceInfo = "Абонемент: 2500 руб./мес.",
            phone = "+7 (917) 222-33-44",
            description = "Студия для занятий йогой",
            rating = 4.7f,
            latitude = 53.62987,
            longitude = 55.95234,
            yandexMapsUrl = "https://yandex.ru/maps/-/CDaJYj8C"
        ),
        SportPlace(
            id = 802,
            name = "Йога-центр 'Гармония'",
            sportTypeId = 9,
            address = "пр. Ленина, 25",
            schedule = "Ежедневно: 07:00-23:00",
            coachInfo = "Инструкторы: 5 специалистов",
            priceInfo = "Разовое: 500 руб.",
            phone = "+7 (3473) 55-44-33",
            description = "Центр различных направлений йоги",
            rating = 4.8f,
            latitude = 53.62654,
            longitude = 55.94321,
            yandexMapsUrl = "https://yandex.ru/maps/-/CDaJYFTS"
        ),

        // БОКС (12 мест)
        SportPlace(
            id = 901,
            name = "Боксерский клуб 'Удар'",
            sportTypeId = 10,
            address = "ул. Фурманова, 15",
            schedule = "Пн-Пт: 17:00-22:00",
            coachInfo = "Тренер: Руслан Каримов",
            priceInfo = "Абонемент: 2200 руб./мес.",
            phone = "+7 (917) 333-44-55",
            description = "Профессиональный боксерский зал",
            rating = 4.6f,
            latitude = 53.62890,
            longitude = 55.94100,
            yandexMapsUrl = "https://yandex.ru/maps/-/CDaJYV2S"
        ),
        SportPlace(
            id = 902,
            name = "Школа бокса 'Чемпион'",
            sportTypeId = 10,
            address = "ул. Космонавтов, 22",
            schedule = "Вт, Чт, Сб: 18:00-21:00",
            coachInfo = "Тренер: Ильдар Хамитов",
            priceInfo = "Детские группы: 1500 руб./мес.",
            phone = "+7 (3473) 77-66-55",
            description = "Бокс для детей и взрослых",
            rating = 4.5f,
            latitude = 53.62123,
            longitude = 55.93654,
            yandexMapsUrl = "https://yandex.ru/maps/-/CDaJYp8D"
        ),

        // ТРЕНАЖЕРНЫЙ ЗАЛ (20 мест)
        SportPlace(
            id = 1001,
            name = "Тренажерный зал 'Атлет'",
            sportTypeId = 11,
            address = "ул. Комсомольская, 45",
            schedule = "Круглосуточно",
            coachInfo = "Дежурный тренер",
            priceInfo = "Абонемент: 1800 руб./мес.",
            phone = "+7 (3473) 44-55-66",
            description = "Тренажерный зал 24/7",
            rating = 4.4f,
            latitude = 53.63345,
            longitude = 55.92890,
            yandexMapsUrl = "https://yandex.ru/maps/-/CDaJY6WS"
        ),
        SportPlace(
            id = 1002,
            name = "Тренажерный зал 'Сила'",
            sportTypeId = 11,
            address = "ул. Гагарина, 30",
            schedule = "Пн-Вс: 06:00-00:00",
            coachInfo = "Тренер: Максим Орлов",
            priceInfo = "Разовое: 300 руб.",
            phone = "+7 (917) 444-55-66",
            description = "Зал с современными тренажерами",
            rating = 4.3f,
            latitude = 53.62700,
            longitude = 55.93345,
            yandexMapsUrl = "https://yandex.ru/maps/-/CDaJYvCC"
        ),
        SportPlace(
            id = 1003,
            name = "Тренажерный зал 'Металлург'",
            sportTypeId = 11,
            address = "ул. Заводская, 18",
            schedule = "Пн-Пт: 07:00-22:00",
            coachInfo = "Тренер: Сергей Ковалев",
            priceInfo = "Корпоративный: 1200 руб./мес.",
            phone = "+7 (3473) 88-99-11",
            description = "Заводской тренажерный зал",
            rating = 4.2f,
            latitude = 53.64123,
            longitude = 55.91234,
            yandexMapsUrl = "https://yandex.ru/maps/-/CDaJY3RG"
        )
        // Остальные места можно добавить по аналогии...
    )

    fun getPlacesBySportType(sportTypeId: Int): List<SportPlace> {
        return sportPlaces.filter { it.sportTypeId == sportTypeId }
    }

    // Функция для поиска по всем местам (для поисковой строки)
    fun searchPlaces(query: String): List<SportPlace> {
        val lowerQuery = query.lowercase()
        return sportPlaces.filter {
            it.name.lowercase().contains(lowerQuery) ||
                    it.address.lowercase().contains(lowerQuery) ||
                    it.description.lowercase().contains(lowerQuery)
        }
    }
}