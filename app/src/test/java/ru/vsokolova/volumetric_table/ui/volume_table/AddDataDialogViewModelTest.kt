package ru.vsokolova.volumetric_table.ui.volume_table

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import ru.vsokolova.volumetric_table.db.volume.VolumeRepository
import org.junit.Assert.assertTrue

class AddDataDialogViewModelTest {
    private lateinit var viewModel: AddDataDialogViewModel
    private lateinit var volumeRepository: VolumeRepository
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup(){
        volumeRepository = Mockito.mock(VolumeRepository::class.java)
        viewModel = AddDataDialogViewModel(volumeRepository)
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @Test
    fun `correct getting list of length`() {
        viewModel.viewModelScope.launch(Dispatchers.Main) {
            viewModel.getLengths("2", false)
        }
        viewModel.lengths.value?.forEach {
            assertTrue(it.startsWith("2"))
        }

    }

//    @Test
//    fun `correct getting list of thicks`() {
//        viewModel.viewModelScope.launch(Dispatchers.Main) {
//            viewModel.getThicks("2", "3", false)
//        }
//        val expectedArray = arrayOf("30", "32", "34", "36", "38")
//        val actualArray = arrayOf(viewModel.thicks.value)
//        assertTrue(expectedArray.contentEquals(actualArray))
//
//    }

    @Test
    fun getVolume() {
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset the main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }

//    @Test
//    fun `ValidateInput gives error when username is invalid`() {
//        viewModel.validateInput("user", "password")
//
//        assertTrue(LiveDataTestUtil.getValue(viewModel.enterDetailsState) is EnterDetailsError)
//    }
//
//    @Test
//    fun `ValidateInput gives error when password is invalid`() {
//        viewModel.validateInput("username", "pass")
//
//        assertTrue(LiveDataTestUtil.getValue(viewModel.enterDetailsState) is EnterDetailsError)
//    }
//
//    @Test
//    fun `ValidateInput succeeds when input is valid`() {
//        viewModel.validateInput("username", "password")
//
//        assertTrue(LiveDataTestUtil.getValue(viewModel.enterDetailsState) is EnterDetailsSuccess)
//    }
}