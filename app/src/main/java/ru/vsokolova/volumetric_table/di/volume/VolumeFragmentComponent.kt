package ru.vsokolova.volumetric_table.di.volume

import dagger.Component
import ru.vsokolova.volumetric_table.di.MainActivityComponent
import ru.vsokolova.volumetric_table.ui.volume_table.AddDataDialogFragment
import ru.vsokolova.volumetric_table.ui.volume_table.AddDataDialogViewModel
import javax.inject.Singleton

@Singleton
@Component(modules = [VolumeDataModule::class],
    dependencies = [MainActivityComponent::class])
interface VolumeFragmentComponent {

    fun inject(fragmentVolume: AddDataDialogFragment)

    fun provideAddDataDialogViewModel(): AddDataDialogViewModel

    companion object {
        fun create(mainActivityComponent: MainActivityComponent): VolumeFragmentComponent {
            return DaggerVolumeFragmentComponent.builder()
                .mainActivityComponent(mainActivityComponent)
                .build()
        }
    }
}