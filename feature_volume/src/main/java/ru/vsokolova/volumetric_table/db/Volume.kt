package ru.vsokolova.volumetric_table.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "volumetric_table")
data class Volume(
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(name = "length") val length: String,
    @ColumnInfo(name = "thick") val thick: String,
    @ColumnInfo(name = "top") val top: Short,
    @ColumnInfo(name = "value") val value: String
)
