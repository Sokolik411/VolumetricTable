package ru.vsokolova.volumetric_table.db

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [Volume::class], version = 1, exportSchema = false)
abstract class VolumeDatabase : RoomDatabase() {
    abstract fun getVolumeDao(): VolumeDao

}