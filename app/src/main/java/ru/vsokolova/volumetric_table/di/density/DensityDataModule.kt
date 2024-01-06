package ru.vsokolova.volumetric_table.di.density

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import ru.vsokolova.volumetric_table.db.density.DensityDao
import ru.vsokolova.volumetric_table.db.density.DensityDatabase
import ru.vsokolova.volumetric_table.db.density.DensityRepository
import ru.vsokolova.volumetric_table.di.ViewModelFactory
import ru.vsokolova.volumetric_table.di.ViewModelKey
import ru.vsokolova.volumetric_table.ui.density.DensityViewModel
import javax.inject.Provider
import javax.inject.Singleton

@Module
class DensityDataModule {

    @Provides
    @IntoMap
    @ViewModelKey(DensityViewModel::class)
    fun provideViewModel(densityRepository: DensityRepository): ViewModel {
        return DensityViewModel(densityRepository)
    }

    @Singleton
    @Provides
    fun provideViewModelFactory(creators: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>): ViewModelFactory {
        return ViewModelFactory(creators)
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