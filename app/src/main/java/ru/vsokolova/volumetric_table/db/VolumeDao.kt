package ru.vsokolova.volumetric_table.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface VolumeDao {
//    @Insert
//    fun insertAll(volumeList: List<Volume>)

    @Query("SELECT value FROM volumetric_table WHERE length = :length AND thick = :thick AND top = :top")
    fun getVolumeValue(length: String, thick: String, top: Short): String

    @Query("SELECT length FROM volumetric_table WHERE length LIKE '%' || :startWith || '%' AND top = :top")
    fun getLengthsList(startWith: String, top: Short): List<String>

//    @Query("SELECT * FROM volumetric_table")
//    fun getAll()

//    @Query("SELECT length FROM volumetric_table WHERE length LIKE :startWith AND top = :")
//    fun getLength(startWith: String): Cursor

//    var2 = var1 + " LIKE '" + var2 + "%' AND " + "top" + "='" + var5 + "' AND " + var3 + "='" + var4 + "'";
}