package co.tiagoaguiar.fitnesstracker.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class Calc(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "type") val type: String,
    @ColumnInfo(name = "response") val response: Double,
    @ColumnInfo(name = "created_date") val createdDate: Date = Date()
)
