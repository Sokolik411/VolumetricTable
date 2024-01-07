package ru.vsokolova.volumetric_table.db

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [Density::class], version = 1, exportSchema = false)
abstract class DensityDatabase : RoomDatabase() {
    abstract fun getDensityDao(): DensityDao
}