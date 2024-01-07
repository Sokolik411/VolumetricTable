package ru.vsokolova.volumetric_table.di

import dagger.Component
import ru.vsokolova.volumetric_table.ui.AddDataDialogFragment
import ru.vsokolova.volumetric_table.ui.AddDataDialogViewModel
import javax.inject.Singleton

@Singleton
@Component(modules = [VolumeDataModule::class],
    dependencies = [AppProvider::class])
interface VolumeFragmentComponent {

    fun inject(fragmentVolume: AddDataDialogFragment)

    fun provideAddDataDialogViewModel(): AddDataDialogViewModel

    companion object {
        fun create(appProvider: AppProvider): VolumeFragmentComponent {
            return DaggerVolumeFragmentComponent.builder()
                .appProvider(appProvider)
                .build()
        }
    }
}