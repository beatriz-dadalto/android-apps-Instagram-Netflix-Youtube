package co.tiagoaguiar.fitnesstracker.model

import androidx.room.TypeConverter
import java.util.Date

object DateConverter {

    // quando for buscar no BD
    @TypeConverter
    fun toDate(dateLong: Long?): Date? {
        return if (dateLong != null) Date(dateLong) else null
    }

    // quando for gravar no BD
    @TypeConverter
    fun fromDate(date: Date?): Long? {
        return date?.time
    }
}