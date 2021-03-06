package com.template.horoscope

import java.time.LocalDate
import java.time.Period

fun getZodiac(day: Int, month: Int): String {
    return when (month) {
        1 -> if (day >= 20) "Водолей" else "Козерог"
        2 -> if (day >= 19) "Рыбы" else "Водолей"
        3 -> if (day >= 21) "Овен" else "Рыбы"
        4 -> if (day >= 20) "Телец" else "Овен"
        5 -> if (day >= 21) "Близнецы" else "Телец"
        6 -> if (day >= 21) "Рак" else "Близнецы"
        7 -> if (day >= 23) "Лев" else "Рак"
        8 -> if (day >= 23) "Дева" else "Лев"
        9 -> if (day >= 23) "Весы" else "Дева"
        10 -> if (day >= 23) "Скорпион" else "Весы"
        11 -> if (day >= 22) "Стрелец" else "Скорпион"
        12 -> if (day >= 22) "Козерог" else "Стрелец"
        else -> "error"
    }
}

fun getZodiacEn(zodiac: String): String {
    return when (zodiac) {
        "Овен" -> "aries"
        "Телец" -> "taurus"
        "Близнецы" -> "gemini"
        "Рак" -> "cancer"
        "Лев" -> "leo"
        "Дева" -> "virgo"
        "Весы" -> "libra"
        "Скорпион" -> "scorpio"
        "Стрелец" -> "sagittarius"
        "Козерог" -> "capricorn"
        "Водолей" -> "aquarius"
        "Рыбы" -> "pisces"
        else -> "error"
    }
}

fun getElement(zodiac: String): String {
    return when (zodiac) {
        "Водолей" -> "Вода"
        "Рыбы" -> "Вода"
        "Скорпион" -> "Вода"
        "Рак" -> "Вода"
        "Телец" -> "Земля"
        "Дева" -> "Земля"
        "Козерог" -> "Земля"
        "Стрелец" -> "Огонь"
        "Овен" -> "Огонь"
        "Лев" -> "Огонь"
        "Весы" -> "Воздух"
        "Близнецы" -> "Воздух"
        else -> "Error"
    }
}

fun getPlanet(zodiac: String): String {
    return when (zodiac) {
        "Водолей" -> "Уран"
        "Рыбы" -> "Нептун"
        "Скорпион" -> "Плутон"
        "Рак" -> "Луна"
        "Телец" -> "Венера"
        "Дева" -> "Меркурий"
        "Козерог" -> "Сатурн"
        "Стрелец" -> "Юпитер"
        "Овен" -> "Марс"
        "Лев" -> "Солнце"
        "Весы" -> "Венера"
        "Близнецы" -> "Меркурий"
        else -> "Error"
    }
}

//для более точного определения нужно учитывать еще месяц и день (Китайский новый год начинается с февраля)
fun chineseZodiac(year: Int): String {
    return when (year % 12) {
        0 -> "Год Обезьяны"
        1 -> "Год Петуха"
        2 -> "Год Собаки"
        3 -> "Год Свиньи"
        4 -> "Год Крысы"
        5 -> "Год Быка"
        6 -> "Год Тигра"
        7 -> "Год Кролика"
        8 -> "Год Дракона"
        9 -> "Год Змеи"
        10 -> "Год Лошади"
        11 -> "Год Овцы"
        else -> "error"
    }
}

fun getBirthdayDate(year: Int, month: Int, day: Int): LocalDate {
    return LocalDate.of(year, month, day)
}

fun getAge(birthday: LocalDate): Int {
    return Period.between(
        birthday,
        LocalDate.now()
    ).years // если год больше нынешнего, то вернет отрицательное число, сделать проверку
}