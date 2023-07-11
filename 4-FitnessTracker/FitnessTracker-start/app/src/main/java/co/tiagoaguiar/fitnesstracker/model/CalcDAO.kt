package co.tiagoaguiar.fitnesstracker.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface CalcDAO {

    @Insert
    fun insert(calc: Calc)

    @Query(value = "SELECT * FROM Calc WHERE type = :type")
    fun getRegisterByType(type: String) : List<Calc>

    @Delete
    fun delete(calc: Calc): Int

    @Update
    fun update(calc: Calc)
}