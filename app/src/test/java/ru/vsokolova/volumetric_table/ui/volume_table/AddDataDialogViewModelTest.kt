package ru.vsokolova.volumetric_table.ui.volume_table

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import ru.vsokolova.volumetric_table.db.volume.VolumeRepository

@OptIn(ExperimentalCoroutinesApi::class)
class AddDataDialogViewModelTest {
    private lateinit var viewModel: AddDataDialogViewModel
    private lateinit var volumeRepository: VolumeRepository

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        volumeRepository = Mockito.mock(VolumeRepository::class.java)
        viewModel = AddDataDialogViewModel(volumeRepository)
        Dispatchers.setMain(StandardTestDispatcher())
    }

    @Test
    fun `correct getting list of length`() {
        runTest {
            Mockito.`when`(volumeRepository.getAllLength(Mockito.anyString(), Mockito.anyShort()))
                .thenReturn(
                    listOf("2", "2.1")
                )
            viewModel.getLengths("2", false)
            advanceUntilIdle()
            viewModel.lengths.value?.forEach {
                assertTrue(it.startsWith("2"))
            }
        }
    }

    @Test
    fun `correct getting list of thicks`() {
        runTest {
            Mockito.`when`(volumeRepository.getAllThick(Mockito.anyString(), Mockito.anyString(), Mockito.anyShort()))
                .thenReturn(
                    listOf("8", "81", "85")
                )
            viewModel.getThicks("8","3", false)
            advanceUntilIdle()
            viewModel.thicks.value?.forEach {
                assertTrue(it.startsWith("8"))
            }
        }
    }

    @Test
    fun `correct getting volume`() {
        runTest {
            Mockito.`when`(volumeRepository.getVolume(Mockito.anyString(), Mockito.anyString(), Mockito.anyShort()))
                .thenReturn(
                    "123"
                )
            viewModel.getVolume("5","4", false)
            advanceUntilIdle()
            assertTrue("123" == viewModel.volume.value)
        }
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}