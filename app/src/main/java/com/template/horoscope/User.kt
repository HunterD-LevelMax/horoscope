package com.template.horoscope

import java.time.LocalDate

data class User(
    var birthdayDate: LocalDate,
    var zodiacSign: String,
    var element: String,
    var planet:String,
    var nameYear: String,
    var age: Int
)






