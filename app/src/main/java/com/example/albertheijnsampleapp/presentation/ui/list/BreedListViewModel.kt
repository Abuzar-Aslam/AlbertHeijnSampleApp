package com.example.albertheijnsampleapp.presentation.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.albertheijnsampleapp.domain.usecase.BreedUseCase
import com.example.albertheijnsampleapp.presentation.model.list.BreedItem
import com.example.albertheijnsampleapp.presentation.uiaction.UiAction
import com.example.albertheijnsampleapp.utils.handleException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

/**
 * ViewModel class for the main screen of the app.
 *
 * This class is responsible for managing the state and business logic related to the main screen.
 * It interacts with the BreedUseCase to retrieve the list of breeds.
 *
 * @param breedUseCase The use case for retrieving the list of breeds.
 */
@HiltViewModel
class BreedListViewModel @Inject constructor(private val breedUseCase: BreedUseCase) : ViewModel() {

    private val _pagingData: MutableStateFlow<PagingData<BreedItem>> =
        MutableStateFlow(PagingData.empty())
    val pagingData: StateFlow<PagingData<BreedItem>> = _pagingData.asStateFlow()

    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    /**
     * Initializes the ViewModel by fetching the breeds.
     */
    init {
        fetchBreeds()
    }

    /**
     * Handles UI actions triggered by the UI.
     *
     * @param action The UI action to handle.
     */
    fun onUiAction(action: UiAction) {
        when (action) {
            is UiAction.RetryAction -> fetchBreeds()
            is UiAction.HandleErrorAction -> {
                val newUiState = UiState(errorMessageId = handleException(action.throwable))
                _uiState.value = newUiState
            }
        }
    }

    /**
     * Fetches the list of breeds from the BreedUseCase.
     */
    fun fetchBreeds() {
        breedUseCase.getBreeds()
            .cachedIn(viewModelScope)
            .onEach { _pagingData.value = it }
            .launchIn(viewModelScope)
    }


}
