package ru.vsokolova.volumetric_table.di.density

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.vsokolova.volumetric_table.db.density.DensityDao
import ru.vsokolova.volumetric_table.db.density.DensityDatabase
import ru.vsokolova.volumetric_table.db.density.DensityRepository
import javax.inject.Singleton

@Module
class DensityDataModule {

    @Provides
    fun provideDensityRepository(densityDao: DensityDao): DensityRepository {
        return DensityRepository(densityDao)
    }

    @Singleton
    @Provides
    fun provideDensityDao(database: DensityDatabase): DensityDao {
        return database.getDensityDao()
    }

    @Singleton
    @Provides
    fun provideDensityDatabase(context: Context): DensityDatabase {
        return Room.databaseBuilder(
            context,
            DensityDatabase::class.java,
            "density_table"
        )
            .createFromAsset("density_table")
            .build()
    }

}