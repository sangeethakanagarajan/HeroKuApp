package com.ex.herokuapp.util

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

object DateUtils {

    fun getTime(dateString: String?): String? {
        return try {
            val format1 = SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss")
            val date: Date = format1.parse(dateString)
            val sdf: DateFormat = SimpleDateFormat("H:mm")
            val netDate: Date = date
            sdf.format(netDate)
        } catch (ex: Exception) {
            ex.printStackTrace()
            "xx"
        }
    }

}