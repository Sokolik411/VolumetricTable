package ru.vsokolova.volumetric_table.ui.test

import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import ru.vsokolova.volumetric_table.db.density.DensityRepository
import ru.vsokolova.volumetric_table.ui.density.DensityViewModel

class DensityViewModelTest {
    private lateinit var viewModel: DensityViewModel
    private lateinit var densityRepository: DensityRepository

    @Before
    fun setup() {
        densityRepository = Mockito.mock(DensityRepository::class.java)
        viewModel = DensityViewModel(densityRepository)
    }

    @Test
    fun `test 1`(){
        Thread.sleep(100)
    }
}