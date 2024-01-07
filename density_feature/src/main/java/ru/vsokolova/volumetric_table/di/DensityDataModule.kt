package ru.vsokolova.volumetric_table.di

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import ru.vsokolova.volumetric_table.db.DensityDao
import ru.vsokolova.volumetric_table.db.DensityDatabase
import ru.vsokolova.volumetric_table.db.DensityRepository
import ru.vsokolova.volumetric_table.ui.DensityViewModel
import javax.inject.Singleton

@Module
class DensityDataModule {

    @Provides
    @IntoMap
    @ViewModelKey(DensityViewModel::class)
    fun provideViewModel(densityRepository: DensityRepository): ViewModel {
        return DensityViewModel(densityRepository)
    }

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