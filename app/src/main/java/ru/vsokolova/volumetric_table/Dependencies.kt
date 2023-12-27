package ru.vsokolova.volumetric_table

import android.content.Context
import androidx.room.Room
import ru.vsokolova.volumetric_table.db.volume.VolumeDatabase
import ru.vsokolova.volumetric_table.db.volume.VolumeRepository
import ru.vsokolova.volumetric_table.db.density.DensityDatabase
import ru.vsokolova.volumetric_table.db.density.DensityRepository

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

    private val densityDatabase: DensityDatabase by lazy {
        Room.databaseBuilder(
            applicationContext,
            DensityDatabase::class.java,
            "density_table"
        )
            .createFromAsset("density_table")
            .build()
    }

    val volumeRepository: VolumeRepository by lazy { VolumeRepository(volumeDatabase.getVolumeDao()) }
    val densityRepository: DensityRepository by lazy { DensityRepository(densityDatabase.getDensityDao()) }
}