package com.template.horoscope

import android.os.Build
import androidx.annotation.RequiresApi
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
fun ChineseZodiac(year: Int): String {
    return when (year % 12) {
        0 -> "Год обезьяны"
        1 -> "Год петуха"
        2 -> "Год собаки"
        3 -> "Год свинья"
        4 -> "Год крысы"
        5 -> "Год быка"
        6 -> "Год тигра"
        7 -> "Год кролика"
        8 -> "Год дракона"
        9 -> "Год змеи"
        10 -> "Год лошади"
        11 -> "Год овцы"
        else -> "error"
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun getBirthdayDate(year: Int, month: Int, day: Int): LocalDate {
    return LocalDate.of(year, month, day)

}

@RequiresApi(Build.VERSION_CODES.O)
fun getAge(birthday: LocalDate): Int {
    return Period.between(
        birthday,
        LocalDate.now()
    ).years // если год больше нынешнего, то вернет отрицательное число, сделать проверку
}