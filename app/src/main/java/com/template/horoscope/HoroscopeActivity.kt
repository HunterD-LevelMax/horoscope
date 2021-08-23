package com.template.horoscope

import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_horoscope.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class HoroscopeActivity : AppCompatActivity() {
    lateinit var user: User

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_horoscope)
        loadData()
        showToast("Загружены данные о пользователе с локального хранилища")
        dateTVH.text =
            user.dateBirthday.format(DateTimeFormatter.ofPattern("dd MMMM yyyy")).toString()
        ageTVH.text = user.age.toString()
        zodiacTVH.text = user.zodiacSign
        nameYearTVH.text = user.nameYear
        nameElementTVH.text = user.element
        namePlanetTVH.text = user.planet
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun loadData() {
        var dateBirthday: LocalDate
        var zodiacSign: String
        var nameElement: String
        var namePlanet: String
        var nameYear: String
        var age: Int

        dateBirthday = LocalDate.parse(
            getSharedPreferences(
                "PREFERENCE",
                Context.MODE_PRIVATE
            ).getString("BIRTHDAY", null)
        )

        zodiacSign = getSharedPreferences("PREFERENCE", Context.MODE_PRIVATE)
            .getString("ZODIAC", null)
            .toString()

        nameYear = getSharedPreferences("PREFERENCE", Context.MODE_PRIVATE)
            .getString("NAME_YEAR", null)
            .toString()

        age = getSharedPreferences("PREFERENCE", Context.MODE_PRIVATE)
            .getString("AGE", null)?.toInt()!!

        nameElement = getSharedPreferences("PREFERENCE", Context.MODE_PRIVATE)
            .getString("ELEMENT", null)
            .toString()

        namePlanet= getSharedPreferences("PREFERENCE", Context.MODE_PRIVATE)
            .getString("PLANET", null)
            .toString()

        user = User(dateBirthday, zodiacSign, nameElement, namePlanet,  nameYear, age)


    }
}