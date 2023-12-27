package ru.vsokolova.volumetric_table.db.volume

import androidx.room.Dao
import androidx.room.Query

@Dao
interface VolumeDao {

    @Query("SELECT value FROM volumetric_table WHERE length = :length AND thick = :thick AND top = :top")
    fun getVolumeValue(length: String, thick: String, top: Short): String

    @Query("SELECT DISTINCT length FROM volumetric_table WHERE length LIKE '%' || :startWith || '%' AND top = :top")
    fun getLengthsList(startWith: String, top: Short): List<String>

    @Query("SELECT DISTINCT thick FROM volumetric_table WHERE thick LIKE '%' || :startWith || '%' AND length = :length AND top = :top")
    fun getThickList(startWith: String, length: String, top: Short): List<String>
}