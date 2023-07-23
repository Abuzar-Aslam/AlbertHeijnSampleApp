package com.example.albertheijnsampleapp.presentation.ui.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.albertheijnsampleapp.R
import com.example.albertheijnsampleapp.data.model.Result
import com.example.albertheijnsampleapp.domain.usecase.BreedDetailUseCase
import com.example.albertheijnsampleapp.presentation.uiaction.UiAction
import com.example.albertheijnsampleapp.utils.handleException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel class for managing the state and data related to the breed detail screen.
 *
 * @param savedStateHandle The SavedStateHandle to retrieve data passed to the ViewModel.
 * @param breedDetailUseCase The [BreedDetailUseCase] responsible for fetching the breed detail data.
 */
@HiltViewModel
class BreedDetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val breedDetailUseCase: BreedDetailUseCase
) : ViewModel() {

    // MutableStateFlow to hold the current state of the breed detail screen.
    private val _breedDetailState = MutableStateFlow<BreedDetailState?>(null)

    // StateFlow representing the current state of the breed detail screen. Consumers can collect
    // this StateFlow to get updates whenever the state changes.
    val breedDetailState: StateFlow<BreedDetailState?> = _breedDetailState.asStateFlow()

    // When the ViewModel is created, automatically fetch the breed detail data.
    init {
        fetchDetail()
    }

    /**
     * Function to fetch the breed detail data based on the breedId retrieved from the
     * SavedStateHandle.
     */
    private fun fetchDetail() {
        // Retrieve the breedId from the SavedStateHandle.
        val breedId = savedStateHandle.get<String>("id")

        // Check if the breedId is not null or empty.
        if (!breedId.isNullOrEmpty()) {
            viewModelScope.launch {
                // Set the Loading state while fetching the data.
                _breedDetailState.value = BreedDetailState.Loading

                // Use the BreedDetailUseCase to get the breed detail data.
                when (val result = breedDetailUseCase.getBreedDetail(breedId)) {
                    // If the result is an error, set the Error state with the appropriate error message.
                    is Result.Error -> _breedDetailState.value = BreedDetailState.Error(
                        handleException(result.exception)
                    )

                    // If the result is a success, set the Success state with the retrieved data.
                    is Result.Success -> _breedDetailState.value =
                        BreedDetailState.Success(result.data)
                }
            }
        } else {
            // If the breedId is null or empty, set the Error state with a "No Result Found" message.
            _breedDetailState.value = BreedDetailState.Error(R.string.no_result_found)
        }
    }

    /**
     * Function to handle UI actions triggered by the UI.
     *
     * @param action The UI action to handle.
     */
    fun onUiAction(action: UiAction) {
        when (action) {
            // If the UI action is a RetryAction, fetch the breed detail data again.
            is UiAction.RetryAction -> fetchDetail()

            // If the UI action is a HandleErrorAction, set the Error state with the throwable
            // exception provided in the action.
            is UiAction.HandleErrorAction -> {
                _breedDetailState.value = BreedDetailState.Error(handleException(action.throwable))
            }
        }
    }
}
