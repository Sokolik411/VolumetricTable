package ru.vsokolova.volumetric_table.di.volume

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.vsokolova.volumetric_table.db.volume.VolumeDao
import ru.vsokolova.volumetric_table.db.volume.VolumeDatabase
import ru.vsokolova.volumetric_table.db.volume.VolumeRepository
import javax.inject.Singleton

@Module
class VolumeDataModule {

    fun provideVolumeRepository(volumeDao: VolumeDao): VolumeRepository {
        return VolumeRepository(volumeDao)
    }

    @Singleton
    @Provides
    fun provideDensityDao(database: VolumeDatabase): VolumeDao {
        return database.getVolumeDao()
    }

    @Singleton
    @Provides
    fun provideVolumeDataBase(context: Context): VolumeDatabase {
        return Room.databaseBuilder(
            context,
            VolumeDatabase::class.java,
            "volumetric_table"
        )
            .createFromAsset("volumetric_table")
            .build()
    }
}