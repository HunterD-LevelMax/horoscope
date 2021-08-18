package com.template.horoscope

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.time.format.DateTimeFormatter

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    var user: User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // переход к окну с гороскопом
        fabNext.setOnClickListener(object : View.OnClickListener {
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onClick(v: View?) {

                if (user == null || user?.age!! <= 0)
                    showToast("Выберите свою дату рождения")
                else replaceActivity(HoroscopeActivity())
            }
        })

        button.setOnClickListener(object : View.OnClickListener {
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onClick(view: View?) {

                dateTV.text = getBirthdayDate(
                    datePicker.year,
                    datePicker.month.plus(1),
                    datePicker.dayOfMonth
                ).format(DateTimeFormatter.ofPattern("dd MMMM yyyy"))
                ageTV.text = "Мой возраст " + getAge(
                    getBirthdayDate(
                        datePicker.year,
                        datePicker.month.plus(1),
                        datePicker.dayOfMonth
                    )
                ).toString()
                zodiacTV.text = getZodiac(datePicker.dayOfMonth, datePicker.month.plus(1))
                nameYearTV.text = ChineseZodiac(datePicker.year)

                //пользователь с данными
                user = User(
                    getBirthdayDate(
                        datePicker.year,
                        datePicker.month.plus(1),
                        datePicker.dayOfMonth
                    ),
                    getZodiac(datePicker.dayOfMonth, datePicker.month.plus(1)),
                    ChineseZodiac(datePicker.year),
                    getAge(
                        getBirthdayDate(
                            datePicker.year,
                            datePicker.month.plus(1),
                            datePicker.dayOfMonth
                        )
                    )
                )
                saveData(user!!)
            }
        })

    }

    private fun saveData(user: User) {
        val sharedPreferences: SharedPreferences =
            getSharedPreferences("PREFERENCE", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.apply {
            putString("ZODIAC", user?.zodiacSign)
            putString("NAME_YEAR", user?.nameYear)
            putString("BIRTHDAY", user?.dateBirthday.toString())
            putString("AGE", user?.age.toString())
            showToast(user?.dateBirthday.toString())

        }.apply()
    }


}