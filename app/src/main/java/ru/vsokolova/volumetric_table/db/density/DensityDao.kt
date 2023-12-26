package ru.vsokolova.volumetric_table.db.density

import androidx.room.Dao
import androidx.room.Query

@Dao
interface DensityDao {

    @Query("SELECT density FROM density_table WHERE wood = :wood AND humidity = :humidity")
    fun getDensityValue(wood: String, humidity: String): String
}