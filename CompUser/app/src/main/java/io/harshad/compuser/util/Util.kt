package io.harshad.compuser.util

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter

object Util {
    @RequiresApi(Build.VERSION_CODES.O)
    fun Long?.changeMillisToDateString(): String {
        val date: LocalDate = this?.let {
            Instant
                .ofEpochMilli(it)
                .atZone(ZoneId.systemDefault())
                .toLocalDate()
        } ?: LocalDate.now()
        return date.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy"))
    }
}