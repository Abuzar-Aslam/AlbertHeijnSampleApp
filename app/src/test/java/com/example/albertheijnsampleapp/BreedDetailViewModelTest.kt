package com.example.albertheijnsampleapp

import androidx.lifecycle.SavedStateHandle
import com.example.albertheijnsampleapp.domain.usecase.BreedDetailUseCase
import com.example.albertheijnsampleapp.presentation.ui.detail.BreedDetailViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.Assert.assertEquals
import com.example.albertheijnsampleapp.data.model.Result
import com.example.albertheijnsampleapp.presentation.ui.detail.BreedDetailState
import io.mockk.every
import kotlinx.coroutines.flow.first
import java.io.IOException

@ExperimentalCoroutinesApi
class BreedDetailViewModelTest {

    private val testCoroutineDispatcher: TestCoroutineDispatcher = TestCoroutineDispatcher()
    private lateinit var breedDetailUseCase: BreedDetailUseCase
    private lateinit var viewModel: BreedDetailViewModel
    private lateinit var testCoroutineScope: TestCoroutineScope
    private lateinit var savedStateHandle: SavedStateHandle

    @Before
    fun setUp() {
        val testCoroutineDispatcher = TestCoroutineDispatcher()
        testCoroutineScope = TestCoroutineScope(testCoroutineDispatcher)
        breedDetailUseCase = mockk()
        savedStateHandle = mockk()
        // Set the Main dispatcher to the test dispatcher
        Dispatchers.setMain(testCoroutineDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testCoroutineScope.cleanupTestCoroutines()
        testCoroutineDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `when getBreedDetail returns success then loading should disappear and state has breed detail`() =
        testCoroutineScope.runBlockingTest {

            // Arrange
            coEvery { breedDetailUseCase.getBreedDetail("1") } returns Result.Success(
                expectedBreedDetailResult
            )
            every { savedStateHandle.get<String>("id") } returns "1"
            viewModel = BreedDetailViewModel(savedStateHandle, breedDetailUseCase)

            // Act & Assert
            val currentState = viewModel.breedDetailState.first()
            assert(currentState is BreedDetailState.Success)
            assertEquals(
                expectedBreedDetailResult,
                (currentState as BreedDetailState.Success).userDetail
            )
        }

    @Test
    fun `when getBreedDetail returns error then show error message and state should be error state`() =
        testCoroutineScope.runBlockingTest {

            val networkException = IOException("Network connection error.")
            // Arrange
            coEvery { breedDetailUseCase.getBreedDetail("1") } returns Result.Error(networkException)
            every { savedStateHandle.get<String>("id") } returns "1"
            viewModel = BreedDetailViewModel(savedStateHandle, breedDetailUseCase)

            // Act & Assert
            val currentState = viewModel.breedDetailState.first()
            assert(currentState is BreedDetailState.Error)
            assertEquals(
                R.string.network_error,
                (currentState as BreedDetailState.Error).errorMessage
            )
        }
}