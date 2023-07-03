package co.tiagoaguiar.fitnesstracker.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CalcDAO {

    @Insert
    fun insert(calc: Calc)

    @Query(value = "SELECT * FROM Calc WHERE type = :type")
    fun getRegisterByType(type: String) : List<Calc>
}