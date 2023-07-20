package com.example.albertheijnsampleapp.presentation.ui.detail

import com.example.albertheijnsampleapp.presentation.model.detail.BreedDetail

/**
 * Sealed class representing the different states of the breed detail screen.
 */
sealed class BreedDetailState {

    /**
     * Represents the loading state when the breed detail data is being fetched.
     */
    object Loading : BreedDetailState()

    /**
     * Represents the success state when the breed detail data is successfully retrieved.
     *
     * @property userDetail The [BreedDetail] object containing the breed details.
     */
    data class Success(val userDetail: BreedDetail) : BreedDetailState()

    /**
     * Represents the error state when there is an issue retrieving the breed detail data.
     *
     * @property errorMessage The error message associated with the error state.
     */
    data class Error(val errorMessage: Int) : BreedDetailState()
}
