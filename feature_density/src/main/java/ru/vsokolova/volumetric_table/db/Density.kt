package ru.vsokolova.volumetric_table.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "density_table")
data class Density(
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(name = "wood") val wood: String,
    @ColumnInfo(name = "humidity") val humidity: String,
    @ColumnInfo(name = "density") val density: String
)
