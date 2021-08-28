package com.template.horoscope

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_horoscope.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import okhttp3.*
import okio.IOException
import org.json.JSONObject
import kotlin.concurrent.thread

class HoroscopeActivity : AppCompatActivity() {
    lateinit var user: User
    lateinit var todayHoroscope: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_horoscope)
        loadData()
        showToast("Загружены данные о пользователе с локального хранилища")
        dateTVH.text =
            user.birthdayDate.format(DateTimeFormatter.ofPattern("dd MMMM yyyy")).toString()
        ageTVH.text = "Мне " + user.age.toString()
        zodiacTVH.text = user.zodiacSign
        nameYearTVH.text = user.nameYear
        nameElementTVH.text = user.element
        namePlanetTVH.text = user.planet
        setImageHoroscope(user.zodiacSign)
        requestServer()
    }

    private fun requestServer() {
        thread {
            val url = "https://horoscopes.rambler.ru/api/front/v1/horoscope/today/${getZodiacEn(user.zodiacSign)}/"
            val urlAll = "https://horoscopes.rambler.ru/api/front/v1/horoscope/today"

            val request = Request.Builder()
                .url(url)
                .addHeader("User-Agent", System.getProperty("http.agent").toString())
                .build()
            val requestAll = Request.Builder()
                .url(urlAll)
                .addHeader("User-Agent", System.getProperty("http.agent").toString())
                .build()


            val client = OkHttpClient()
            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    showToast("Ошибка 404")
                }

                override fun onResponse(call: Call, response: Response) {
                    val json = response.body?.string()

                    var filtered = "<p>h12/"

                    todayHoroscope = (JSONObject(json).get("text")).toString()
                        .filterNot { filtered.indexOf(it) > -1 }

                    runOnUiThread() {
                        progressBar.visibility = View.INVISIBLE

                        newHoroscopeTV.text = todayHoroscope
                    }
                }
            })

            val clientAll = OkHttpClient()
            clientAll.newCall(requestAll).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    showToast("Ошибка 404")
                }

                override fun onResponse(call: Call, response: Response) {
                    val json = response.body?.string()

                    var filtered = "</a><p><ahref=\\\"/moon/calendar/\\\">"

                    todayHoroscope = (JSONObject(json).get("text")).toString()
                        .filterNot { filtered.indexOf(it) > -1 }

                    runOnUiThread() {
                        progressBar.visibility = View.INVISIBLE

                        newHoroscopeTV2.text = todayHoroscope
                    }
                }
            })






        }
    }

    fun setImageHoroscope(horoscopeSigns: String) {
        when (horoscopeSigns) {
            "Овен" -> zodiac_icon.setImageResource(R.drawable.aries)
            "Телец" -> zodiac_icon.setImageResource(R.drawable.taurus)
            "Близнецы" -> zodiac_icon.setImageResource(R.drawable.gemini)
            "Рак" -> zodiac_icon.setImageResource(R.drawable.cancer)
            "Лев" -> zodiac_icon.setImageResource(R.drawable.leo)
            "Дева" -> zodiac_icon.setImageResource(R.drawable.virgo)
            "Весы" -> zodiac_icon.setImageResource(R.drawable.libra)
            "Скорпион" -> zodiac_icon.setImageResource(R.drawable.scorpio)
            "Стрелец" -> zodiac_icon.setImageResource(R.drawable.sagittarius)
            "Козерог" -> zodiac_icon.setImageResource(R.drawable.capricorn)
            "Водолей" -> zodiac_icon.setImageResource(R.drawable.aquarius)
            "Рыбы" -> zodiac_icon.setImageResource(R.drawable.pisces)
        }
    }

    fun loadData() {

        var dateBirthday = LocalDate.parse(
            getSharedPreferences(
                "PREFERENCE",
                Context.MODE_PRIVATE
            ).getString("BIRTHDAY", null)
        )

        var zodiacSign = getSharedPreferences("PREFERENCE", Context.MODE_PRIVATE)
            .getString("ZODIAC", null)
            .toString()

        var nameYear = getSharedPreferences("PREFERENCE", Context.MODE_PRIVATE)
            .getString("NAME_YEAR", null)
            .toString()

        var age = getSharedPreferences("PREFERENCE", Context.MODE_PRIVATE)
            .getString("AGE", null)?.toInt()!!

        var nameElement = getSharedPreferences("PREFERENCE", Context.MODE_PRIVATE)
            .getString("ELEMENT", null)
            .toString()

        var namePlanet = getSharedPreferences("PREFERENCE", Context.MODE_PRIVATE)
            .getString("PLANET", null)
            .toString()

        user = User(dateBirthday, zodiacSign, nameElement, namePlanet, nameYear, age)
    }

}