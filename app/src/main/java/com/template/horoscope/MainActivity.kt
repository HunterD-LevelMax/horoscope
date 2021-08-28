package com.template.horoscope

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.time.LocalDate

import java.time.format.DateTimeFormatter

class MainActivity : AppCompatActivity() {

    var user: User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var dateBirthday = LocalDate.parse(
            getSharedPreferences(
                "PREFERENCE",
                Context.MODE_PRIVATE
            ).getString("BIRTHDAY", null)
        ).toString()

        if (dateBirthday!=null){
            replaceActivity(HoroscopeActivity())
        }

        // переход к окну с гороскопом
        fabNext.setOnClickListener {
            if (user == null || user?.age!! <= 0) showToast("Выберите свою дату рождения")
            else startHoroscope()
            replaceActivity(HoroscopeActivity())
        }

        button.setOnClickListener {
            startHoroscope()
            replaceActivity(HoroscopeActivity())
        }
    }

    fun startHoroscope() {
        var day = datePicker.dayOfMonth
        var month = datePicker.month.plus(1)
        var year = datePicker.year

        dateTV.text =
            getBirthdayDate(year, month, day).format(DateTimeFormatter.ofPattern("dd MMMM yyyy"))
        zodiacTV.text = getZodiac(day, month)
        nameElementTV.text = getElement(getZodiac(day, month))
        namePlanetTV.text = getPlanet(getZodiac(day, month))
        nameYearTV.text = chineseZodiac(year)
        ageTV.text = getAge(getBirthdayDate(year, month, day)).toString()


        //пользователь с данными
        user = User(
            getBirthdayDate(year, month, day),
            getZodiac(day, month),
            chineseZodiac(year),
            getElement(getZodiac(day, month)),
            getPlanet(getZodiac(day, month)),
            getAge(getBirthdayDate(year, month, day))
        )
        saveData(user!!)
    }


    private fun saveData(user: User) {
        val sharedPreferences: SharedPreferences =
            getSharedPreferences("PREFERENCE", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.apply {
            putString("ZODIAC", user?.zodiacSign)
            putString("ELEMENT", user?.element)
            putString("PLANET", user?.planet)
            putString("NAME_YEAR", user?.nameYear)
            putString("BIRTHDAY", user?.birthdayDate.toString())
            putString("AGE", user?.age.toString())
        }.apply()
    }
}