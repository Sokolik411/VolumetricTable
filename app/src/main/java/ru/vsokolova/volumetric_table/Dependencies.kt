package ru.vsokolova.volumetric_table

import android.content.Context
import androidx.room.Room
import ru.vsokolova.volumetric_table.db.volume.VolumeDatabase
import ru.vsokolova.volumetric_table.db.volume.VolumeRepository

object Dependencies {

    private lateinit var applicationContext: Context

    fun init(context: Context){
        applicationContext = context
    }

    private val volumeDatabase: VolumeDatabase by lazy {
        Room.databaseBuilder(
            applicationContext,
            VolumeDatabase::class.java,
            "volumetric_table"
        )
            .createFromAsset("volumetric_table")
            .build()
    }

    val volumeRepository: VolumeRepository by lazy { VolumeRepository(volumeDatabase.getVolumeDao()) }
}